package com.freedom.wishlist.infrastructure.repository;

import com.freedom.wishlist.domain.wishlist.Wishlist;
import com.freedom.wishlist.domain.wishlist.WishlistMapper;
import com.freedom.wishlist.domain.wishlist.repository.WishlistRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WishlistRepository implements WishlistRepositoryInterface {

    private final WishlistDao wishlistDao;
    private final WishlistMapper wishlistMapper;

    @Override
    public Optional<Wishlist> findWishlistByIdAndCustomerId(String id, String customerId) {
        return wishlistDao.findByIdAndCustomerId(id, customerId).map(wishlistMapper::wishlistModelToWishlist);
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        return wishlistMapper.wishlistModelToWishlist(
                wishlistDao.save(wishlistMapper.wishlistToWishlistModel(wishlist))
        );
    }
}
