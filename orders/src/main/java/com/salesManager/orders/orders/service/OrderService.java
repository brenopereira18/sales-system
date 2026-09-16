package com.salesManager.orders.orders.service;

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
}
