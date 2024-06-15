package com.freedom.wishlist.domain.exception;

public class FullWishlistException extends RuntimeException {

    public FullWishlistException() {
        super("message.exception.wishlist.full");
    }
}
