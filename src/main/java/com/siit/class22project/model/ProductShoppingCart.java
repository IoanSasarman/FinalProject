package com.siit.class22project.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "shoppingcart")
public class ProductShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartitemid", nullable = false, updatable = false)
    private Long cartItemId;
    @Column(name = "userid", nullable = false)
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "currency")
    private String currency;
}
