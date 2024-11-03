package com.siit.class22project;

import com.siit.class22project.model.Product;
import com.siit.class22project.model.ProductShoppingCart;
import com.siit.class22project.repository.ProductJPARepository;
import com.siit.class22project.repository.ShoppingCartJPARepository;
import com.siit.class22project.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartJPARepository shoppingCartJPARepository;

    @Mock
    private ProductJPARepository productJPARepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addToCart_ShouldSaveProductToShoppingCart() {
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(productId);
        when(productJPARepository.findById(productId)).thenReturn(Optional.of(mockProduct));
        when(shoppingCartJPARepository.save(any(ProductShoppingCart.class))).thenReturn(mockProduct.toShoppingCart());

        shoppingCartService.addToCart(productId);

        verify(shoppingCartJPARepository, times(1)).save(any(ProductShoppingCart.class));
    }

    @Test
    void deleteProductShoppingCartById_ShouldDeleteProduct() {
        Long productId = 1L;
        ProductShoppingCart mockProductInCart = new ProductShoppingCart();
        when(shoppingCartJPARepository.findById(productId)).thenReturn(Optional.of(mockProductInCart));

        shoppingCartService.deleteProductShoppingCartById(productId);

        verify(shoppingCartJPARepository, times(1)).deleteById(productId);
    }

    @Test
    void clearShoppingCart_ShouldDeleteAllProductsInCart() {
        shoppingCartService.clearShoppingCart();

        verify(shoppingCartJPARepository, times(1)).deleteAll();
    }
}
