package com.freedom.wishlist.infrastructure.resource;

import com.freedom.wishlist.MongoBaseTest;
import com.freedom.wishlist.domain.exception.FullWishlistException;
import com.freedom.wishlist.domain.exception.ProductNotFoundException;
import com.freedom.wishlist.domain.exception.WishlistNotFoundException;
import com.freedom.wishlist.domain.wishlist.request.AddProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.request.RemoveProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.request.VerifyProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.response.AddProductWishlistResponse;
import com.freedom.wishlist.domain.wishlist.response.ProductsWishlistResponse;
import com.freedom.wishlist.domain.wishlist.response.VerifyProductWishlistResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Testcontainers
@SpringBootTest
public class WishlistControllerTest extends MongoBaseTest {

    @Autowired
    private WishlistController wishlistController;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void whenProductNotExists_thenReturn404() {
        wishlistInitialize(mongoTemplate);
        productInitialize(mongoTemplate);

        AddProductWishlistRequest request = new AddProductWishlistRequest("1", "1", "23");

        assertThatThrownBy(
                () -> wishlistController.addProductWishlist(request))
                .isInstanceOf(ProductNotFoundException.class);

        dropAllWishlist(mongoTemplate);
        dropAllProduct(mongoTemplate);
    }

    @Test
    void whenWishlistNotExists_thenReturn404() {
        wishlistInitialize(mongoTemplate);
        productInitialize(mongoTemplate);

        AddProductWishlistRequest request = new AddProductWishlistRequest("3", "1", "1");

        assertThatThrownBy(
                () -> wishlistController.addProductWishlist(request))
                .isInstanceOf(WishlistNotFoundException.class);

        dropAllWishlist(mongoTemplate);
        dropAllProduct(mongoTemplate);
    }

    @Test
    void whenWishlistIsFull_thenReturn422() {
        wishlistInitialize(mongoTemplate);
        productInitialize(mongoTemplate);

        AddProductWishlistRequest request = new AddProductWishlistRequest("1", "1", "21");

        assertThatThrownBy(
                () -> wishlistController.addProductWishlist(request))
                .isInstanceOf(FullWishlistException.class);

        dropAllWishlist(mongoTemplate);
        dropAllProduct(mongoTemplate);
    }

    @Test
    void whenWishlistExists_thenAddProduct() {
        wishlistInitialize(mongoTemplate);
        productInitialize(mongoTemplate);

        AddProductWishlistRequest request = new AddProductWishlistRequest("2", "1", "1");

        AddProductWishlistResponse response = wishlistController.addProductWishlist(request);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getIdProducts());
        Assertions.assertEquals(1, response.getIdProducts().size());

        dropAllWishlist(mongoTemplate);
        dropAllProduct(mongoTemplate);
    }

    @Test
    void whenWishlistExistsAndHaveProducts_thenReturnProductsList() {
        wishlistInitialize(mongoTemplate);
        productInitialize(mongoTemplate);

        ProductsWishlistResponse response = wishlistController.getAllProductsByWishlistIdAndCustomerId("1", "1");

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getProducts());
        Assertions.assertEquals(20, response.getProducts().size());

        dropAllWishlist(mongoTemplate);
        dropAllProduct(mongoTemplate);
    }

    @Test
    void whenWishListExistsAndContainProduct_thenReturnTrue() {
        wishlistInitialize(mongoTemplate);

        VerifyProductWishlistRequest request = new VerifyProductWishlistRequest("1", "1", "1");

        VerifyProductWishlistResponse response = wishlistController.verifyProductExistsWishlist(request);

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isData());

        dropAllWishlist(mongoTemplate);
    }

    @Test
    void whenWishListExistsAndNotContainProduct_thenReturnFalse() {
        wishlistInitialize(mongoTemplate);

        VerifyProductWishlistRequest request = new VerifyProductWishlistRequest("1", "1", "50");

        VerifyProductWishlistResponse response = wishlistController.verifyProductExistsWishlist(request);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isData());

        dropAllWishlist(mongoTemplate);
    }

    @Test
    void deveRemoverProdutoDaWishList() {
        wishlistInitialize(mongoTemplate);
        productInitialize(mongoTemplate);

        RemoveProductWishlistRequest request = new RemoveProductWishlistRequest("1", "1", "1");
        wishlistController.removeProductWishlist(request);

        ProductsWishlistResponse response = wishlistController.getAllProductsByWishlistIdAndCustomerId("1", "1");

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getProducts());
        Assertions.assertEquals(19, response.getProducts().size());
        dropAllWishlist(mongoTemplate);
        dropAllProduct(mongoTemplate);
    }
}
