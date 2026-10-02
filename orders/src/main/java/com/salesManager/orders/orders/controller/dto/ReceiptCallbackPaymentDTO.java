package com.salesManager.orders.orders.controller.dto;

public record ReceiptCallbackPaymentDTO(Long orderId, String paymentKey, boolean success, String observation) {
}
