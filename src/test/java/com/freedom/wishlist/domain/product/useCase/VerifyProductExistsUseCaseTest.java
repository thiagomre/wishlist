package com.freedom.wishlist.domain.product.useCase;

import com.freedom.wishlist.domain.product.Product;
import com.freedom.wishlist.domain.product.repository.ProductRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VerifyProductExistsUseCaseTest {

    private VerifyProductExistsUseCase verifyProductExistsUseCase;

    @Mock
    private ProductRepositoryInterface productRepositoryInterface;

    @BeforeEach
    void init() {
        verifyProductExistsUseCase = new VerifyProductExistsUseCase(productRepositoryInterface);
    }

    @Test
    void shouldVerifyProductExists() {
        String productId = "1";
        Product product = new Product("1", "Product 1");

        when(productRepositoryInterface.findById(any())).thenReturn(Optional.of(product));

        Boolean response = verifyProductExistsUseCase.verifyProductById(productId);

        verify(productRepositoryInterface).findById(any());
        assertNotNull(response);
    }
}
