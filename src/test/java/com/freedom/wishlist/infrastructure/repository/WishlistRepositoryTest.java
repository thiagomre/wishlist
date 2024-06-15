package com.freedom.wishlist.infrastructure.repository;

import com.freedom.wishlist.MongoBaseTest;
import com.freedom.wishlist.domain.wishlist.Wishlist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
public class WishlistRepositoryTest extends MongoBaseTest {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void shouldSaveWishlist() {
        Set<String> produtoList = new HashSet<>();
        produtoList.add("produto");
        Wishlist wishlist = new Wishlist("23", "1", produtoList);
        Wishlist dataModel = this.wishlistRepository.save(wishlist);

        assertNotNull(dataModel);
    }

    @Test
    void shouldfindWishlistByIdAndCustomerId() {
        wishlistInitialize(mongoTemplate);
        String wishlistId = "1";
        String customerId = "1";
        Optional<Wishlist> wishlistOptional = this.wishlistRepository
                .findWishlistByIdAndCustomerId(wishlistId, customerId);

        assertEquals(wishlistId, wishlistOptional.get().getId());
        assertEquals(customerId, wishlistOptional.get().getCustomerId());
        dropAllWishlist(mongoTemplate);
    }

}
