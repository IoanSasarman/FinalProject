package com.siit.class22project.repository;

import com.siit.class22project.model.Order;
import com.siit.class22project.model.ProductShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartJPARepository extends JpaRepository<ProductShoppingCart, Long> {
}
