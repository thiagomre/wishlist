package com.freedom.wishlist.domain.product.useCase;

import com.freedom.wishlist.domain.product.Product;
import com.freedom.wishlist.domain.product.repository.ProductRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetAllProductsUseCase {

    private final ProductRepositoryInterface productRepositoryInterface;

    public Set<Product> getAllProducts(Set<String> productsIds) {
        return productRepositoryInterface.findByIdIn(productsIds);
    }
}
