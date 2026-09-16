package com.salesManager.orders.orders.repository;

import com.salesManager.orders.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
