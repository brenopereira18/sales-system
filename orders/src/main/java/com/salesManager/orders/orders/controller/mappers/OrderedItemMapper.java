package com.salesManager.orders.orders.controller.mappers;

import com.salesManager.orders.orders.controller.dto.OrderedItemDTO;
import com.salesManager.orders.orders.model.OrderedItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderedItemMapper {

    OrderedItem map(OrderedItemDTO dto);
}
