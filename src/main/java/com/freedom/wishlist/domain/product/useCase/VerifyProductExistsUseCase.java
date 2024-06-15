package com.freedom.wishlist.domain.product.useCase;

import com.freedom.wishlist.domain.product.repository.ProductRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyProductExistsUseCase {

    private final ProductRepositoryInterface productRepositoryInterface;

    public boolean verifyProductById(String productId) {
        return productRepositoryInterface.findById(productId).isPresent();
    }
}
