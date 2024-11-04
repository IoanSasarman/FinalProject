package com.siit.class22project.service;

import com.siit.class22project.exception.BusinessException;
import com.siit.class22project.model.Product;
import com.siit.class22project.model.ProductShoppingCart;
import com.siit.class22project.repository.ProductJPARepository;
import com.siit.class22project.repository.ShoppingCartJPARepository;
import com.siit.class22project.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ProductShoppingCart productShoppingCart = new ProductShoppingCart();
        productShoppingCart.setName(product.getName());
        productShoppingCart.setPrice(product.getPrice());
        productShoppingCart.setCurrency(product.getCurrency());
        productShoppingCart.setUserId(UserUtil.getCurrentUserId());
        shoppingCartJPARepository.save(productShoppingCart);
    }

    public List<ProductShoppingCart> getCurrentUserShoppingCartProducts(Long userId) {
        return shoppingCartJPARepository.findAllByUserId(userId)
                .stream()
                .toList();
    }

    @Transactional
    public void deleteProductShoppingCartByCartItemId(Long cartItemId) {
        Optional<ProductShoppingCart> productShoppingCart = shoppingCartJPARepository.findByCartItemId(cartItemId);
        if (productShoppingCart.isPresent()) {
            shoppingCartJPARepository.deleteByCartItemId(cartItemId);
        } else {
            throw new BusinessException("The product with cart_item_id " + cartItemId + " you are trying to remove from your shopping cart " +
                    "does not exist in our database");
        }
    }

    @Transactional
    public void clearCurrentUserShoppingCart(Long userId) {
        shoppingCartJPARepository.deleteAllByUserId(userId);
    }
}

