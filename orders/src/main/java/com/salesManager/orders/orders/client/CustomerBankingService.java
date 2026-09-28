package com.salesManager.orders.orders.client;

import com.salesManager.orders.orders.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CustomerBankingService {

    public String requestPayment(Order order) {
        log.info("requesting payment for the order with code: {} ", order.getId());
        return UUID.randomUUID().toString();
    }
}
