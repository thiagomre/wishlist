package com.freedom.wishlist.domain.exception;

public class WishlistNotFoundException extends RuntimeException {

    public WishlistNotFoundException() {
        super("message.exception.wishlist.notfound");
    }
}
