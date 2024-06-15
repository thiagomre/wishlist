package com.freedom.wishlist.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("wishlist")
@Data
@AllArgsConstructor
public class WishlistModel {

    @Id
    private String id;
    private String customerId;
    private Set<String> idProducts;
}
