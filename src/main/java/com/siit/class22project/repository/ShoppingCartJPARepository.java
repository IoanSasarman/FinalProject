package com.siit.class22project.repository;

import com.siit.class22project.model.ProductShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartJPARepository extends JpaRepository<ProductShoppingCart, Long> {
    List<ProductShoppingCart> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    Optional<ProductShoppingCart> findByCartItemId(Long cartItemId);
    void deleteByCartItemId(Long cartItemId);
}
