package com.siit.class22project.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "shoppingcart")
public class ProductShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "currency")
    private String currency;
}
