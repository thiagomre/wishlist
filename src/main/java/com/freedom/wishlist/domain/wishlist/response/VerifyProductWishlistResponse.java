package com.freedom.wishlist.domain.wishlist.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VerifyProductWishlistResponse {

    private boolean data = false;

    public VerifyProductWishlistResponse(boolean data) {
        this.data = data;
    }
}
