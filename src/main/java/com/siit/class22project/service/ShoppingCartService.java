package com.siit.class22project.service;

import com.siit.class22project.exception.BusinessException;
import com.siit.class22project.model.Product;
import com.siit.class22project.model.ProductShoppingCart;
import com.siit.class22project.repository.ProductJPARepository;
import com.siit.class22project.repository.ShoppingCartJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartService {

    private final ShoppingCartJPARepository shoppingCartJPARepository;
    private final ProductJPARepository productJPARepository;

    public void addToCart(Long id) {
        Product product = productJPARepository.findById(id)
                .orElseThrow(() -> new BusinessException("Product with id" + id + "cannot be found"));
        shoppingCartJPARepository.save(product.toShoppingCart());
    }

    public List<ProductShoppingCart> getShoppingCartProducts(Pageable pageable) {
        return shoppingCartJPARepository.findAll(pageable)
                .stream()
                .toList();
    }

    public void deleteProductShoppingCartById(Long id) {
        Optional<ProductShoppingCart> productShoppingCart = shoppingCartJPARepository.findById(id);
        if (productShoppingCart.isPresent()) {
            shoppingCartJPARepository.deleteById(id);
        } else {
            throw new BusinessException("The product with id " + id + " you are trying to remove from your shopping cart " +
                    "does not exist in our database");
        }
    }

    public void clearShoppingCart() {
        shoppingCartJPARepository.deleteAll();
    }
}

