package com.salesManager.orders.orders.repository;

import com.salesManager.orders.orders.model.Order;
import com.salesManager.orders.orders.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
    List<OrderedItem> findByOrder(Order order);
}
