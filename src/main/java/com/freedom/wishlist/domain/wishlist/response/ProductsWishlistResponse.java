package com.freedom.wishlist.domain.wishlist.response;

import com.freedom.wishlist.domain.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductsWishlistResponse {

    private Set<Product> products = new HashSet<>();

    public void add(Product product) {
        this.products.add(product);
    }
}
