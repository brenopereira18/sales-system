package com.salesManager.orders.orders.publisher;

import com.salesManager.orders.orders.model.Order;
import com.salesManager.orders.orders.model.OrderedItem;
import com.salesManager.orders.orders.publisher.representation.DetailItemRequestRepresentation;
import com.salesManager.orders.orders.publisher.representation.DetailOrderRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetailOrderMapper {

    @Mapping(source = "dataClient.name", target = "name")
    @Mapping(source = "dataClient.cpf", target = "cpf")
    @Mapping(source = "dataClient.streetAddress", target = "streetAddress")
    @Mapping(source = "dataClient.houseNumber", target = "houseNumber")
    @Mapping(source = "dataClient.neighborhood", target = "neighborhood")
    @Mapping(source = "dataClient.email", target = "email")
    @Mapping(source = "dataClient.cellPhoneNumber", target = "cellPhoneNumber")
    @Mapping(source = "items", target = "itens")
    DetailOrderRepresentation map(Order order);

    DetailItemRequestRepresentation map(OrderedItem item);
}
