package com.salesManager.orders.orders.client;

import com.salesManager.orders.orders.client.representation.ClientRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clients", url = "${salesManager.orders.clients.clients.url}", path = "/clients")
public interface ClientServiceClient {

    @GetMapping("{id}")
    ResponseEntity<ClientRepresentation> getClientById(@PathVariable("id") Long id);

}
