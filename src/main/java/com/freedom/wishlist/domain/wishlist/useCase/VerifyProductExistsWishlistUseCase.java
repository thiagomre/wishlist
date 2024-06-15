package com.freedom.wishlist.domain.wishlist.useCase;

import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import com.freedom.wishlist.domain.wishlist.Wishlist;
import com.freedom.wishlist.domain.wishlist.repository.WishlistRepositoryInterface;
import com.freedom.wishlist.domain.wishlist.response.VerifyProductWishlistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyProductExistsWishlistUseCase {

    private final WishlistRepositoryInterface wishlistRepositoryInterface;

    public VerifyProductWishlistResponse verifyProductExists(Wishlist wishlist) {
        Wishlist wishlistEntity = wishlistRepositoryInterface.findWishlistByIdAndCustomerId(
                        wishlist.getId(), wishlist.getCustomerId())
                .orElseThrow(() -> new WishlistNotFoundException());

        var productId = wishlist.getIdProducts().stream().findFirst().get();
        var result = wishlistEntity.getIdProducts().contains(productId);
        return new VerifyProductWishlistResponse(result);
    }
}
