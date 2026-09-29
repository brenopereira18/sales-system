package com.salesManager.orders.orders.model;

import com.salesManager.orders.orders.model.enums.PaymentType;
import lombok.Data;

@Data
public class PaymentData {

    private String data;
    private PaymentType paymentType;
}
