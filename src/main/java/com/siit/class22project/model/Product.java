package com.siit.class22project.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "currency")
    private String currency;
    private double profit;


    public ProductReturnDto toReturnDto() {
        ProductReturnDto productReturnDto = new ProductReturnDto();
        productReturnDto.setName(name);
        productReturnDto.setPrice(price);
        productReturnDto.setId(this.id);
        productReturnDto.setCurrency(currency);

        return productReturnDto;
    }

    public ProductShoppingCart toShoppingCart() {
        ProductShoppingCart productShoppingCart = new ProductShoppingCart();
//        productShoppingCart.setId(id);
        productShoppingCart.setCurrency(currency);
        productShoppingCart.setName(name);
        productShoppingCart.setPrice(price);

        return productShoppingCart;
    }


}
