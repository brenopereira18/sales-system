package com.salesManager.orders.orders.model;

public record ErrorResponse(String message, String field, String error) {
}
