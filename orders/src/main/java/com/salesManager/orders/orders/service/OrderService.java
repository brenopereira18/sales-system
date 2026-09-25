package com.salesManager.orders.orders.service;

import com.salesManager.orders.orders.model.Order;
import com.salesManager.orders.orders.repository.OrderRepository;
import com.salesManager.orders.orders.repository.OrderedItemRepository;
import com.salesManager.orders.orders.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedItemRepository orderedItemRepository;
    private final OrderValidator orderValidator;

    public Order createOrder(Order order) {
        orderRepository.save(order);
        orderedItemRepository.saveAll(order.getItems());
        return order;
    }
}
