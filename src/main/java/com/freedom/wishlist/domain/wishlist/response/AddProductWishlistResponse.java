package com.freedom.wishlist.domain.wishlist.response;

import com.freedom.wishlist.domain.wishlist.Wishlist;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class AddProductWishlistResponse {

    private String id;
    private String customerId;
    private Set<String> idProducts;

    public AddProductWishlistResponse(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.customerId = wishlist.getCustomerId();
        this.idProducts = wishlist.getIdProducts();
    }
}
