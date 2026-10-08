package com.salesManager.orders.orders.client.representation;

public record ClientRepresentation(Long id, String name, String cpf, String streetAddress, String houseNumber,
                                   String neighborhood, String email, String cellPhoneNumber) {
}
