package com.freedom.wishlist.infrastructure.repository;

import com.freedom.wishlist.infrastructure.entity.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.Set;


public interface ProductDao extends MongoRepository<ProductModel, String> {

    Optional<ProductModel> findById(String productId);

    Set<ProductModel> findByIdIn(Set<String> productIds);
}
