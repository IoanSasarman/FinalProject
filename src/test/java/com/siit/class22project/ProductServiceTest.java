package com.siit.class22project;

import com.siit.class22project.model.Product;
import com.siit.class22project.model.ProductUpdateDto;
import com.siit.class22project.repository.ProductJPARepository;
import com.siit.class22project.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductJPARepository productJPARepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteProductById_ShouldDeleteProduct() {
        Long productId = 1L;
        Product mockProduct = new Product();
        when(productJPARepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        productService.deleteProductById(productId);

        verify(productJPARepository, times(1)).deleteById(productId);
    }

    @Test
    void updateProduct_ShouldUpdateAndSaveProduct() {
        ProductUpdateDto productUpdateDto = new ProductUpdateDto();
        productUpdateDto.setId(1L);
        Product mockProduct = new Product();
        when(productJPARepository.findById(productUpdateDto.getId())).thenReturn(Optional.of(mockProduct));

        productService.updateProduct(productUpdateDto);

        assertEquals(productUpdateDto.getName(), mockProduct.getName());
        assertEquals(productUpdateDto.getPrice(), mockProduct.getPrice());
        verify(productJPARepository, times(1)).save(mockProduct);
    }
}
