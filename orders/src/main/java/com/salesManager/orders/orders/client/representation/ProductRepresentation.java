package com.salesManager.orders.orders.client.representation;

import java.math.BigDecimal;

public record ProductRepresentation(Long id, String name, BigDecimal unitValue) {
}
