package com.freedom.wishlist.domain.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("message.exception.product.notfound");
    }
}
