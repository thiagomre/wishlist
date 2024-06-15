package com.freedom.wishlist.domain.wishlist;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Wishlist {

    private static final int PRODUCT_LIMIT = 20;

    private String id;
    private String customerId;
    private Set<String> idProducts;

    public boolean isFullWishlist() {
        if (this.idProducts == null)
            return false;
        return this.idProducts.size() >= PRODUCT_LIMIT;
    }

}
