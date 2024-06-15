package com.freedom.wishlist.domain.wishlist.useCase;

import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import com.freedom.wishlist.domain.product.Product;
import com.freedom.wishlist.domain.product.useCase.GetAllProductsUseCase;
import com.freedom.wishlist.domain.wishlist.Wishlist;
import com.freedom.wishlist.domain.wishlist.repository.WishlistRepositoryInterface;
import com.freedom.wishlist.domain.wishlist.response.ProductsWishlistResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllProductsWishlistUseCaseTest {

    private GetAllProductsWishlistUseCase getAllProductsWishlistUseCase;

    @Mock
    private WishlistRepositoryInterface wishlistRepositoryInterface;

    @Mock
    private GetAllProductsUseCase getAllProductsUseCase;

    @BeforeEach
    void init() {
        getAllProductsWishlistUseCase = new GetAllProductsWishlistUseCase(wishlistRepositoryInterface, getAllProductsUseCase);
    }

    @Test
    void shouldVerifyProductExistsInWishlist() {
        Set<String> productsIds = new HashSet<>(Set.of("1"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);
        Product product = new Product("1", "Product 1");
        Set<Product> productSet = Set.of(product);

        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.of(wishlist));
        when(getAllProductsUseCase.getAllProducts(productsIds)).thenReturn(productSet);

        ProductsWishlistResponse response = getAllProductsWishlistUseCase
                .getAllProducts(wishlist.getId(), wishlist.getCustomerId());

        assertNotNull(response);
        assertEquals(1, response.getProducts().size());
    }

    @Test
    void shouldReturnWishlistNotFoundWhenWishlistNotExists() {
        Set<String> productsIds = new HashSet<>(Set.of("1"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);

        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getAllProductsWishlistUseCase.getAllProducts(wishlist.getId(), wishlist.getCustomerId()))
                .isInstanceOf(WishlistNotFoundException.class);
    }
}
