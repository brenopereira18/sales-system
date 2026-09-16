package com.salesManager.orders.orders.controller;

import com.salesManager.orders.orders.controller.dto.NewOrderDTO;
import com.salesManager.orders.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    public ResponseEntity create(@RequestBody NewOrderDTO newOrderDTO) {
        return null;
    }
}
