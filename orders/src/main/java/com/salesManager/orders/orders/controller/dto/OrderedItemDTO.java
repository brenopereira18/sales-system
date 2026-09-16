package com.salesManager.orders.orders.controller.dto;

import java.math.BigDecimal;

public record OrderedItemDTO(Long productId, Integer quantity, BigDecimal unitValue) {
}
