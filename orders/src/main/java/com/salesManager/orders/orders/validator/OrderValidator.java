package com.salesManager.orders.orders.validator;

import com.salesManager.orders.orders.client.ClientServiceClient;
import com.salesManager.orders.orders.client.ProductsClient;
import com.salesManager.orders.orders.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final ProductsClient productsClient;
    private final ClientServiceClient clientServiceClient;

    public void validade(Order order) {

    }
}
