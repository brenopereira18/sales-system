package com.salesManager.orders.orders.controller.dto;

import java.util.List;

public record NewOrderDTO(Long clientId, PaymentDataDTO paymentDataDTO, List<OrderedItemDTO> items) {
}
