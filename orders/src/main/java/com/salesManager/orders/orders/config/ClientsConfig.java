package com.salesManager.orders.orders.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.salesManager.orders.orders.client")
public class ClientsConfig {
}
