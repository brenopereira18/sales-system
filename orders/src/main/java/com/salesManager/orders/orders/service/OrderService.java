package com.salesManager.orders.orders.service;

import com.salesManager.orders.orders.client.CustomerBankingService;
import com.salesManager.orders.orders.exception.ResourceNotFoundException;
import com.salesManager.orders.orders.model.Order;
import com.salesManager.orders.orders.model.PaymentData;
import com.salesManager.orders.orders.model.enums.OrderStatus;
import com.salesManager.orders.orders.model.enums.PaymentType;
import com.salesManager.orders.orders.repository.OrderRepository;
import com.salesManager.orders.orders.repository.OrderedItemRepository;
import com.salesManager.orders.orders.validator.OrderValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedItemRepository orderedItemRepository;
    private final OrderValidator orderValidator;
    private final CustomerBankingService customerBankingService;

    @Transactional
    public Order createOrder(Order order) {
        orderValidator.validade(order);
        dataPersistence(order);
        sendPaymentRequest(order);
        return order;
    }

    private void sendPaymentRequest(Order order) {
        String paymentKey = customerBankingService.requestPayment(order);
        order.setPaymentKey(paymentKey);
    }

    private void dataPersistence(Order order) {
        orderRepository.save(order);
        orderedItemRepository.saveAll(order.getItems());
    }

    public void updatePaymentStatus(Long orderId, String paymentKey, boolean success, String observation) {
        orderRepository.findByIdAndPaymentKey(orderId, paymentKey)
            .ifPresentOrElse(order -> updateOrderStatus(order, success, observation),
                () -> log.warn("Order not found."));
    }

    public void updateOrderStatus(Order order, boolean success, String observation) {
        if (success) {
            order.setStatus(OrderStatus.PAID);
        } else {
            order.setStatus(OrderStatus.PAYMENT_ERROR);
            order.setObservation(observation);
        }
        orderRepository.save(order);
    }

    @Transactional
    public void addNewPayment(Long orderId, String cardData, PaymentType paymentType) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            log.warn("Order not found with ID: {} ", orderId);
            return new ResourceNotFoundException("Order not found");
        });

        PaymentData paymentData = new PaymentData();
        paymentData.setData(cardData);
        paymentData.setPaymentType(paymentType);

        order.setPaymentData(paymentData);
        order.setStatus(OrderStatus.REALIZED);
        order.setObservation("New payment made, waiting for new processing");

        String newPaymentKey = customerBankingService.requestPayment(order);
        order.setPaymentKey(newPaymentKey);

        orderRepository.save(order);
    }
}
