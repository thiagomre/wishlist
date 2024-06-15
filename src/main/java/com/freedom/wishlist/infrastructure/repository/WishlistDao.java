package com.freedom.wishlist.infrastructure.repository;

import com.freedom.wishlist.infrastructure.entity.WishlistModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistDao extends MongoRepository<WishlistModel, String> {

    Optional<WishlistModel> findByIdAndCustomerId(String id, String customerId);
}
