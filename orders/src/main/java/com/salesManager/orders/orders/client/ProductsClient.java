package com.salesManager.orders.orders.client;

import com.salesManager.orders.orders.client.representation.ProductRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", url = "${salesManager.orders.clients.products.url}", path = "/products")
public interface ProductsClient {

    @GetMapping("{id}")
    ResponseEntity<ProductRepresentation> getProductById(@PathVariable("id") Long id);
}
