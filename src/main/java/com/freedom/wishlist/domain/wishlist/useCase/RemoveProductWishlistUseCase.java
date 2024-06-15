package com.freedom.wishlist.domain.wishlist.useCase;

import com.freedom.wishlist.domain.exception.ProductNotFoundException;
import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import com.freedom.wishlist.domain.product.useCase.VerifyProductExistsUseCase;
import com.freedom.wishlist.domain.wishlist.Wishlist;
import com.freedom.wishlist.domain.wishlist.repository.WishlistRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveProductWishlistUseCase {

    private final WishlistRepositoryInterface wishlistRepositoryInterface;

    private final VerifyProductExistsUseCase verifyProductExistsUseCase;

    public void remove(Wishlist wishlist) {
        Wishlist wishlistEntity = wishlistRepositoryInterface.findWishlistByIdAndCustomerId(
                        wishlist.getId(), wishlist.getCustomerId())
                .orElseThrow(() -> new WishlistNotFoundException());

        if (!verifyProductExistsUseCase.verifyProductById(wishlist.getIdProducts().stream().findFirst().get()))
            throw new ProductNotFoundException();

        wishlistEntity.getIdProducts().removeAll(wishlist.getIdProducts());
        wishlistRepositoryInterface.save(wishlistEntity);
    }
}
