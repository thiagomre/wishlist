package com.freedom.wishlist.domain.wishlist.useCase;

import com.freedom.wishlist.domain.exception.ProductNotFoundException;
import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import com.freedom.wishlist.domain.product.useCase.VerifyProductExistsUseCase;
import com.freedom.wishlist.domain.wishlist.Wishlist;
import com.freedom.wishlist.domain.wishlist.repository.WishlistRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoveProductWishlistUseCaseTest {

    private RemoveProductWishlistUseCase removeProductWishlistUseCase;

    @Mock
    private WishlistRepositoryInterface wishlistRepositoryInterface;

    @Mock
    private VerifyProductExistsUseCase verifyProductExistsUseCase;

    @BeforeEach
    void init() {
        removeProductWishlistUseCase = new RemoveProductWishlistUseCase(wishlistRepositoryInterface, verifyProductExistsUseCase);
    }

    @Test
    void shouldRemoveProductInWishlist() {
        Set<String> productsIds = new HashSet<>(Set.of("1", "2"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);

        Set<String> productsIdsResponse = new HashSet<>(Set.of("1"));
        Wishlist wishlistResponse = new Wishlist("1", "1", productsIdsResponse);
        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.of(wishlist));
        when(verifyProductExistsUseCase.verifyProductById(any())).thenReturn(true);
        when(wishlistRepositoryInterface.save(any())).thenReturn(wishlistResponse);

        removeProductWishlistUseCase.remove(wishlistResponse);

        verify(wishlistRepositoryInterface).save(wishlist);
    }

    @Test
    void shouldReturnWishlistNotFoundWhenWishlistNotExists() {
        Set<String> productsIds = new HashSet<>(Set.of("1"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);

        Set<String> productsIdsResponse = new HashSet<>(Set.of("1", "2"));
        Wishlist wishlistResponse = new Wishlist("1", "1", productsIdsResponse);
        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> removeProductWishlistUseCase.remove(wishlistResponse))
                .isInstanceOf(WishlistNotFoundException.class);
    }

    @Test
    void shouldReturnProductNotFoundWhenProductNotExists() {
        Set<String> productsIds = new HashSet<>(Set.of("1"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);

        Set<String> productsIdsResponse = new HashSet<>(Set.of("1", "2"));
        Wishlist wishlistResponse = new Wishlist("1", "1", productsIdsResponse);
        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.of(wishlist));
        when(verifyProductExistsUseCase.verifyProductById(any())).thenReturn(false);

        assertThatThrownBy(() -> removeProductWishlistUseCase.remove(wishlistResponse))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
