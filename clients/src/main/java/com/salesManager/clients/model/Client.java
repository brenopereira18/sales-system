package com.salesManager.clients.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(name = "street_address", length = 100)
    private String streetAddress;

    @Column(name = "house_number", length = 10)
    private String houseNumber;

    @Column(length = 100)
    private String neighborhood;

    @Column(length = 150)
    private String email;

    @Column(name = "cell_phone_number", length = 20)
    private String cellPhoneNumber;
}
