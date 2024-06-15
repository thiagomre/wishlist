package com.freedom.wishlist.domain.wishlist.useCase;

import com.freedom.wishlist.domain.exception.FullWishlistException;
import com.freedom.wishlist.domain.exception.ProductNotFoundException;
import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import com.freedom.wishlist.domain.product.useCase.VerifyProductExistsUseCase;
import com.freedom.wishlist.domain.wishlist.Wishlist;
import com.freedom.wishlist.domain.wishlist.repository.WishlistRepositoryInterface;
import com.freedom.wishlist.domain.wishlist.response.AddProductWishlistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProductWishlistUseCase {

    private final WishlistRepositoryInterface wishlistRepositoryInterface;

    private final VerifyProductExistsUseCase verifyProductExistsUseCase;

    public AddProductWishlistResponse add(Wishlist wishlist) {
        Wishlist wishlistEntity = wishlistRepositoryInterface.findWishlistByIdAndCustomerId(
                        wishlist.getId(), wishlist.getCustomerId())
                .orElseThrow(() -> new WishlistNotFoundException());

        if (!verifyProductExistsUseCase.verifyProductById(wishlist.getIdProducts().stream().findFirst().get()))
            throw new ProductNotFoundException();

        if (wishlistEntity.isFullWishlist())
            throw new FullWishlistException();

        wishlistEntity.getIdProducts().addAll(wishlist.getIdProducts());

        return new AddProductWishlistResponse(wishlistRepositoryInterface.save(wishlistEntity));
    }
}
