package com.salesManager.products.controller;

import com.salesManager.products.model.Product;
import com.salesManager.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
