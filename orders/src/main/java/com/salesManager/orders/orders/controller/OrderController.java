package com.salesManager.orders.orders.controller;

import com.salesManager.orders.orders.controller.dto.NewOrderDTO;
import com.salesManager.orders.orders.controller.mappers.OrderMapper;
import com.salesManager.orders.orders.exception.ValidationException;
import com.salesManager.orders.orders.model.ErrorResponse;
import com.salesManager.orders.orders.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper mapper;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody NewOrderDTO newOrderDTO) {
        try {
            var order = mapper.map(newOrderDTO);
            var newOrder = orderService.createOrder(order);
            return ResponseEntity.ok(newOrder.getId());
        } catch (ValidationException e) {
           ErrorResponse error = new ErrorResponse("Error validation", e.getField(), e.getMessage());
           return ResponseEntity.badRequest().body(error);
        }
    }
}
