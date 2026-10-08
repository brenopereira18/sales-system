package com.salesManager.orders.orders.publisher.representation;

import com.salesManager.orders.orders.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DetailOrderRepresentation(
    Long id,
    Long clientId,
    String name,
    String cpf,
    String streetAddress,
    String houseNumber,
    String neighborhood,
    String email,
    String cellPhoneNumber,
    LocalDateTime orderDate,
    BigDecimal total,
    OrderStatus status,
    List<DetailItemRequestRepresentation> itens
) {
}
