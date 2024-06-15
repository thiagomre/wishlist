package com.freedom.wishlist.domain.wishlist.useCase;

import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import com.freedom.wishlist.domain.wishlist.Wishlist;
import com.freedom.wishlist.domain.wishlist.repository.WishlistRepositoryInterface;
import com.freedom.wishlist.domain.wishlist.response.VerifyProductWishlistResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class VerifyProductExistsWishlistUseCaseTest {

    private VerifyProductExistsWishlistUseCase verifyProductExistsWishlistUseCase;

    @Mock
    private WishlistRepositoryInterface wishlistRepositoryInterface;

    @BeforeEach
    void init() {
        verifyProductExistsWishlistUseCase = new VerifyProductExistsWishlistUseCase(wishlistRepositoryInterface);
    }

    @Test
    void shouldVerifyProductExistsInWishlist() {
        Set<String> productsIds = new HashSet<>(Set.of("1", "2"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);

        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.of(wishlist));

        VerifyProductWishlistResponse response = verifyProductExistsWishlistUseCase.verifyProductExists(wishlist);

        assertNotNull(response);
        assertTrue(response.isData());
    }

    @Test
    void shouldReturnWishlistNotFoundWhenWishlistNotExists() {
        Set<String> productsIds = new HashSet<>(Set.of("1"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);

        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> verifyProductExistsWishlistUseCase.verifyProductExists(wishlist))
                .isInstanceOf(WishlistNotFoundException.class);
    }

}
