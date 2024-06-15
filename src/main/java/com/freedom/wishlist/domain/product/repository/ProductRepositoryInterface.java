package com.freedom.wishlist.domain.product.repository;

import com.freedom.wishlist.domain.product.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductRepositoryInterface {

    Optional<Product> findById(String productId);

    Set<Product> findByIdIn(Set<String> productIds);
}
