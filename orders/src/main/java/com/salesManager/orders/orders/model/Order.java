package com.salesManager.orders.orders.model;

import com.salesManager.orders.orders.client.representation.ClientRepresentation;
import com.salesManager.orders.orders.model.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "payment_key")
    private String paymentKey;

    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus status;

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal total;

    @Column(name = "tracking_code", length = 255)
    private String trackingCode;

    @Column(name = "url_nf")
    private String urlNf;

    @Transient
    private PaymentData paymentData;

    @OneToMany(mappedBy = "order")
    private List<OrderedItem> items;

    @Transient
    private ClientRepresentation dataClient;
}
