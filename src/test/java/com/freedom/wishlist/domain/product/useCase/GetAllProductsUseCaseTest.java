package com.freedom.wishlist.domain.product.useCase;

import com.freedom.wishlist.domain.product.Product;
import com.freedom.wishlist.domain.product.repository.ProductRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllProductsUseCaseTest {

    GetAllProductsUseCase getAllProductsUseCase;

    @Mock
    private ProductRepositoryInterface productRepositoryInterface;

    @BeforeEach
    void init() {
        getAllProductsUseCase = new GetAllProductsUseCase(productRepositoryInterface);
    }

    @Test
    void shouldReturnAllProducts() {
        Set<String> productsIds = Set.of("1");
        Product product = new Product("1", "Product 1");
        Set<Product> productSet = Set.of(product);
        when(productRepositoryInterface.findByIdIn(any())).thenReturn(productSet);

        Set<Product> response = getAllProductsUseCase.getAllProducts(productsIds);
        verify(productRepositoryInterface).findByIdIn(any());
        assertNotNull(response);
    }
}
