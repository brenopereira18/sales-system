package com.salesManager.orders.orders.controller.mappers;

import com.salesManager.orders.orders.controller.dto.NewOrderDTO;
import com.salesManager.orders.orders.controller.dto.OrderedItemDTO;
import com.salesManager.orders.orders.model.Order;
import com.salesManager.orders.orders.model.enums.OrderStatus;
import com.salesManager.orders.orders.model.OrderedItem;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderedItemMapper ORDERED_ITEM_MAPPER = Mappers.getMapper(OrderedItemMapper.class);

    @Mapping(source = "items", target = "items", qualifiedByName = "mapItems")
    @Mapping(source = "paymentData", target = "paymentData")
    Order map(NewOrderDTO dto);

    @Named("mapItems")
    default List<OrderedItem> mapItems(List<OrderedItemDTO> dtos) {
        return dtos.stream().map(ORDERED_ITEM_MAPPER::map).toList();
    }

    @AfterMapping
    default void afterMapping(@MappingTarget Order order) {
        order.setStatus(OrderStatus.REALIZED);
        order.setOrderDate(LocalDateTime.now());

        var total = totalValue(order);
        order.setTotal(total);

        order.getItems().forEach(item -> item.setOrder(order));
    }

    private static BigDecimal totalValue(Order order) {
        return order.getItems().stream().map(item ->
            item.getUnitValue().multiply(BigDecimal.valueOf(item.getQuantity()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add).abs();
    }
}
