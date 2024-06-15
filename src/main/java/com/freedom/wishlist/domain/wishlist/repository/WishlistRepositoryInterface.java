package com.freedom.wishlist.domain.wishlist.repository;

import com.freedom.wishlist.domain.wishlist.Wishlist;

import java.util.Optional;

public interface WishlistRepositoryInterface {

    Optional<Wishlist> findWishlistByIdAndCustomerId(String id, String customerId);

    Wishlist save(Wishlist wishlist);

}
