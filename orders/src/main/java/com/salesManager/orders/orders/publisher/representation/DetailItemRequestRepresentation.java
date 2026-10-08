package com.salesManager.orders.orders.publisher.representation;

import java.math.BigDecimal;

public record DetailItemRequestRepresentation(Long productId, String name, Integer quantity, BigDecimal unitValue) {

    public BigDecimal getTotal() {
        return unitValue.multiply(BigDecimal.valueOf(quantity));
    }
}
