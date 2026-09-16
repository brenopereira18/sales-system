package com.salesManager.orders.orders.controller.dto;

import com.salesManager.orders.orders.model.PaymentType;

public record PaymentDataDTO(String data, PaymentType paymentType) {
}
