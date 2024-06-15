package com.freedom.wishlist.domain.wishlist.useCase;

import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import com.freedom.wishlist.domain.product.useCase.GetAllProductsUseCase;
import com.freedom.wishlist.domain.wishlist.Wishlist;
import com.freedom.wishlist.domain.wishlist.repository.WishlistRepositoryInterface;
import com.freedom.wishlist.domain.wishlist.response.ProductsWishlistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetAllProductsWishlistUseCase {

    private final WishlistRepositoryInterface wishlistRepositoryInterface;
    private final GetAllProductsUseCase getAllProductsUseCase;

    public ProductsWishlistResponse getAllProducts(String wishlistId, String customerId) {
        Wishlist wishlist = wishlistRepositoryInterface
                .findWishlistByIdAndCustomerId(wishlistId, customerId)
                .orElseThrow(() -> new WishlistNotFoundException());

        Set<String> products = wishlist.getIdProducts();
        var response = new ProductsWishlistResponse();
        getAllProductsUseCase.getAllProducts(products)
                .forEach(product -> response.add(product));
        return response;
    }
}
