package com.salesManager.orders.orders.service;

import com.salesManager.orders.orders.client.CustomerBankingService;
import com.salesManager.orders.orders.model.Order;
import com.salesManager.orders.orders.repository.OrderRepository;
import com.salesManager.orders.orders.repository.OrderedItemRepository;
import com.salesManager.orders.orders.validator.OrderValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
}
