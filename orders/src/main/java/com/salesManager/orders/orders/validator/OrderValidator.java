package com.salesManager.orders.orders.validator;

import com.salesManager.orders.orders.client.ClientServiceClient;
import com.salesManager.orders.orders.client.ProductsClient;
import com.salesManager.orders.orders.exception.ValidationException;
import com.salesManager.orders.orders.model.Order;
import com.salesManager.orders.orders.model.OrderedItem;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final ProductsClient productsClient;
    private final ClientServiceClient clientServiceClient;

    public void validade(Order order) {
        Long clientId = order.getClientId();
        validateClient(clientId);
        order.getItems().forEach(this::validateItem);

    }

    private void validateClient(Long clientId) {
        try {
            var response = clientServiceClient.getClientById(clientId);
        } catch (FeignException.NotFound e) {
            String message = String.format("Id %d client not foud", clientId);
            throw new ValidationException("clientId", message);
        }
    }

    private void validateItem(OrderedItem item) {
        try {
            var response = productsClient.getProductById(item.getProductId());
        } catch (FeignException.NotFound e) {
            String message = String.format("Id %d product not found", item.getProductId());
            throw new ValidationException("productId", message);
        }
    }
}
