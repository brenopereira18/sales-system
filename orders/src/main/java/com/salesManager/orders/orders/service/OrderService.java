package com.salesManager.orders.orders.service;

import com.salesManager.orders.orders.client.ClientServiceClient;
import com.salesManager.orders.orders.client.CustomerBankingService;
import com.salesManager.orders.orders.client.ProductsClient;
import com.salesManager.orders.orders.client.representation.ClientRepresentation;
import com.salesManager.orders.orders.client.representation.ProductRepresentation;
import com.salesManager.orders.orders.exception.ResourceNotFoundException;
import com.salesManager.orders.orders.model.Order;
import com.salesManager.orders.orders.model.OrderedItem;
import com.salesManager.orders.orders.model.PaymentData;
import com.salesManager.orders.orders.model.enums.OrderStatus;
import com.salesManager.orders.orders.model.enums.PaymentType;
import com.salesManager.orders.orders.repository.OrderRepository;
import com.salesManager.orders.orders.repository.OrderedItemRepository;
import com.salesManager.orders.orders.validator.OrderValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedItemRepository orderedItemRepository;
    private final OrderValidator orderValidator;
    private final CustomerBankingService customerBankingService;
    private final ClientServiceClient apiClient;
    private final ProductsClient apiProduct;

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

    public Order getCompleteOrderData(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));

        getDataClient(order);
        getOrderItem(order);

        return order;
    }

    private void getDataClient(Order order) {
        Long id = order.getClientId();
        ResponseEntity<ClientRepresentation> response = apiClient.getClientById(id);
        order.setDataClient(response.getBody());

    }

    private void getOrderItem(Order order) {
        List<OrderedItem> itens = orderedItemRepository.findByOrder(order);
        order.setItems(itens);
        order.getItems().forEach(this::getDataProduct);
    }

    private void getDataProduct(OrderedItem item) {
        Long productId = item.getProductId();
        ResponseEntity<ProductRepresentation> response = apiProduct.getProductById(productId);
        item.setName(response.getBody().name());
    }
}
