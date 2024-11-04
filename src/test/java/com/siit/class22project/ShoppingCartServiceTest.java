package com.siit.class22project;

import com.siit.class22project.model.ProductShoppingCart;
import com.siit.class22project.repository.ShoppingCartJPARepository;
import com.siit.class22project.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartJPARepository shoppingCartJPARepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private final Long userId = 1L;
    private final Long cartItemId = 1L;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCurrentUserShoppingCartProducts() {
        ProductShoppingCart cartItem1 = new ProductShoppingCart();
        cartItem1.setName("TestProduct1");
        ProductShoppingCart cartItem2 = new ProductShoppingCart();
        cartItem2.setName("TestProduct2");
        when(shoppingCartJPARepository.findAllByUserId(userId)).thenReturn(List.of(cartItem1, cartItem2));

        List<ProductShoppingCart> products = shoppingCartService.getCurrentUserShoppingCartProducts(userId);

        assertEquals(2, products.size());
        verify(shoppingCartJPARepository, times(1)).findAllByUserId(userId);
    }

    @Test
    public void testDeleteProductShoppingCartByCartItemId() {
        when(shoppingCartJPARepository.findByCartItemId(cartItemId)).thenReturn(Optional.of(new ProductShoppingCart()));

        shoppingCartService.deleteProductShoppingCartByCartItemId(cartItemId);

        verify(shoppingCartJPARepository, times(1)).deleteByCartItemId(cartItemId);
    }

    @Test
    public void testClearCurrentUserShoppingCart() {
        shoppingCartService.clearCurrentUserShoppingCart(userId);

        verify(shoppingCartJPARepository, times(1)).deleteAllByUserId(userId);
    }
}
