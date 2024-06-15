package com.freedom.wishlist.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
@Data
@AllArgsConstructor
public class ProductModel {

    @Id
    private String id;
    private String name;
}
