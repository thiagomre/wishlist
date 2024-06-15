package com.freedom.wishlist.domain.wishlist.useCase;

import com.freedom.wishlist.domain.exception.FullWishlistException;
import com.freedom.wishlist.domain.exception.ProductNotFoundException;
import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import com.freedom.wishlist.domain.product.useCase.VerifyProductExistsUseCase;
import com.freedom.wishlist.domain.wishlist.Wishlist;
import com.freedom.wishlist.domain.wishlist.repository.WishlistRepositoryInterface;
import com.freedom.wishlist.domain.wishlist.response.AddProductWishlistResponse;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddProductWishlistUseCaseTest {

    private AddProductWishlistUseCase addProductWishlistUseCase;

    @Mock
    private WishlistRepositoryInterface wishlistRepositoryInterface;

    @Mock
    private VerifyProductExistsUseCase verifyProductExistsUseCase;

    @BeforeEach
    void init() {
        addProductWishlistUseCase = new AddProductWishlistUseCase(wishlistRepositoryInterface, verifyProductExistsUseCase);
    }

    @Test
    void shouldAddProductInWishlist() {
        Set<String> productsIds = new HashSet<>(Set.of("1"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);

        Set<String> productsIdsResponse = new HashSet<>(Set.of("1", "2"));
        Wishlist wishlistResponse = new Wishlist("1", "1", productsIdsResponse);
        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.of(wishlist));
        when(verifyProductExistsUseCase.verifyProductById(any())).thenReturn(true);
        when(wishlistRepositoryInterface.save(any())).thenReturn(wishlistResponse);

        AddProductWishlistResponse response = addProductWishlistUseCase.add(wishlistResponse);

        verify(wishlistRepositoryInterface).save(wishlist);
        assertNotNull(response);
    }

    @Test
    void shouldReturnWishlistNotFoundWhenWishlistNotExists() {
        Set<String> productsIds = new HashSet<>(Set.of("1"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);

        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> addProductWishlistUseCase.add(wishlist))
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

        assertThatThrownBy(() -> addProductWishlistUseCase.add(wishlistResponse))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void shouldReturnFullWishlistExceptionWhenLimitProductMoreThan20() {
        Set<String> productsIds = new HashSet<>(
                Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"));
        Wishlist wishlist = new Wishlist("1", "1", productsIds);

        Set<String> productsIdsResponse = new HashSet<>(Set.of("1", "2"));
        Wishlist wishlistResponse = new Wishlist("1", "1", productsIdsResponse);
        when(wishlistRepositoryInterface.findWishlistByIdAndCustomerId(any(), any())).thenReturn(Optional.of(wishlist));
        when(verifyProductExistsUseCase.verifyProductById(any())).thenReturn(true);

        assertThatThrownBy(() -> addProductWishlistUseCase.add(wishlistResponse))
                .isInstanceOf(FullWishlistException.class);
    }
}
