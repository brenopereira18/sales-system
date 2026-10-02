package com.salesManager.orders.orders.controller;

import com.salesManager.orders.orders.controller.dto.ReceiptCallbackPaymentDTO;
import com.salesManager.orders.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/callback-payment")
@RequiredArgsConstructor
public class ReceiptCallbackPaymentController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> updatePaymentStatus(@RequestBody ReceiptCallbackPaymentDTO body, @RequestHeader(required = true, name="apiKey") String apiKey) {

        orderService.updatePaymentStatus(body.orderId(), body.paymentKey(), body.success(), body.observation());

        return ResponseEntity.ok().build();
    }
}
