package com.freedom.wishlist.infrastructure.repository;

import com.freedom.wishlist.MongoBaseTest;
import com.freedom.wishlist.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
public class ProductRepositoryTest extends MongoBaseTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void shouldfindProductById() {
        productInitialize(mongoTemplate);
        String productId = "1";
        Optional<Product> product = this.productRepository.findById(productId);

        assertNotNull(product.get());
        dropAllProduct(mongoTemplate);
    }

    @Test
    void shouldfindProductByIdIn() {
        productInitialize(mongoTemplate);
        Set<String> productIds = Set.of("1", "2", "3", "4");
        Set<Product> productSet = this.productRepository
                .findByIdIn(productIds);

        assertEquals(productIds.size(), productSet.size());
        dropAllProduct(mongoTemplate);
    }

}
