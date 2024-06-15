package com.freedom.wishlist.infrastructure.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freedom.wishlist.MongoBaseTest;
import com.freedom.wishlist.config.exception.ErrorResponse;
import com.freedom.wishlist.domain.wishlist.WishlistMapper;
import com.freedom.wishlist.domain.wishlist.request.AddProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.request.RemoveProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.request.VerifyProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.useCase.AddProductWishlistUseCase;
import com.freedom.wishlist.domain.wishlist.useCase.GetAllProductsWishlistUseCase;
import com.freedom.wishlist.domain.wishlist.useCase.RemoveProductWishlistUseCase;
import com.freedom.wishlist.domain.wishlist.useCase.VerifyProductExistsWishlistUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WishlistController.class)
public class WishlistControllerMockMvcTest extends MongoBaseTest {

    @Autowired
    private WishlistController wishlistController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WishlistMapper wishlistMapper;
    @MockBean
    private AddProductWishlistUseCase addProductWishlistUseCase;
    @MockBean
    private RemoveProductWishlistUseCase removeProductWishlistUseCase;
    @MockBean
    private GetAllProductsWishlistUseCase getAllProductsWishlistUseCase;
    @MockBean
    private VerifyProductExistsWishlistUseCase verifyProductExistsWishlist;

    @Test
    void whenIdIsNullAddProduct_thenReturns400() throws Exception {
        AddProductWishlistRequest request = new AddProductWishlistRequest();
        request.setIdProduct("1");
        request.setCustomerId("1");

        mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenIdIsBlankAddProduct_thenReturns400() throws Exception {
        AddProductWishlistRequest request = new AddProductWishlistRequest();
        request.setId("");
        request.setIdProduct("1");
        request.setCustomerId("1");

        MvcResult mvcResult = mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        errorResponse.addFieldError("id", "The 'id' field must not be empty or blank");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(errorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void whenIdProductIsNullAddProduct_thenReturns400() throws Exception {
        AddProductWishlistRequest request = new AddProductWishlistRequest();
        request.setId("1");
        request.setCustomerId("1");

        mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenIdProductIsBlankAddProduct_thenReturns400() throws Exception {
        AddProductWishlistRequest request = new AddProductWishlistRequest();
        request.setId("1");
        request.setIdProduct("");
        request.setCustomerId("1");

        MvcResult mvcResult = mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        errorResponse.addFieldError("idProduct", "The 'idProduct' field must not be empty or blank");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(errorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void whenCustomerIdIsNullAddProduct_thenReturns400() throws Exception {
        AddProductWishlistRequest request = new AddProductWishlistRequest();
        request.setId("1");
        request.setIdProduct("1");

        mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCustomerIdIsBlankAddProduct_thenReturns400() throws Exception {
        AddProductWishlistRequest request = new AddProductWishlistRequest();
        request.setId("1");
        request.setIdProduct("1");
        request.setCustomerId("");

        MvcResult mvcResult = mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        errorResponse.addFieldError("customerId", "The 'customerId' field must not be empty or blank");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(errorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void whenIdIsNullRemoveProduct_thenReturns400() throws Exception {
        RemoveProductWishlistRequest request = new RemoveProductWishlistRequest();
        request.setIdProduct("1");
        request.setCustomerId("1");

        mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenIdIsBlankRemoveProduct_thenReturns400() throws Exception {
        RemoveProductWishlistRequest request = new RemoveProductWishlistRequest();
        request.setId("");
        request.setIdProduct("1");
        request.setCustomerId("1");

        MvcResult mvcResult = mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        errorResponse.addFieldError("id", "The 'id' field must not be empty or blank");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(errorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void whenIdProductIsNullRemoveProduct_thenReturns400() throws Exception {
        RemoveProductWishlistRequest request = new RemoveProductWishlistRequest();
        request.setId("1");
        request.setCustomerId("1");

        mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenIdProductIsBlankRemoveProduct_thenReturns400() throws Exception {
        RemoveProductWishlistRequest request = new RemoveProductWishlistRequest();
        request.setId("1");
        request.setIdProduct("");
        request.setCustomerId("1");

        MvcResult mvcResult = mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        errorResponse.addFieldError("idProduct", "The 'idProduct' field must not be empty or blank");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(errorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void whenCustomerIdIsNullRemoveProduct_thenReturns400() throws Exception {
        RemoveProductWishlistRequest request = new RemoveProductWishlistRequest();
        request.setId("1");
        request.setIdProduct("1");

        mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCustomerIdIsBlankRemoveProduct_thenReturns400() throws Exception {
        RemoveProductWishlistRequest request = new RemoveProductWishlistRequest();
        request.setId("1");
        request.setIdProduct("1");
        request.setCustomerId("");

        MvcResult mvcResult = mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        errorResponse.addFieldError("customerId", "The 'customerId' field must not be empty or blank");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(errorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void whenIdIsNullVerifyProduct_thenReturns400() throws Exception {
        VerifyProductWishlistRequest request = new VerifyProductWishlistRequest();
        request.setIdProduct("1");
        request.setCustomerId("1");

        mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenIdIsBlankVerifyProduct_thenReturns400() throws Exception {
        VerifyProductWishlistRequest request = new VerifyProductWishlistRequest();
        request.setId("");
        request.setIdProduct("1");
        request.setCustomerId("1");

        MvcResult mvcResult = mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        errorResponse.addFieldError("id", "The 'id' field must not be empty or blank");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(errorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void whenIdProductIsNullVerifyProduct_thenReturns400() throws Exception {
        VerifyProductWishlistRequest request = new VerifyProductWishlistRequest();
        request.setId("1");
        request.setCustomerId("1");

        mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenIdProductIsBlankVerifyProduct_thenReturns400() throws Exception {
        VerifyProductWishlistRequest request = new VerifyProductWishlistRequest();
        request.setId("1");
        request.setIdProduct("");
        request.setCustomerId("1");

        MvcResult mvcResult = mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        errorResponse.addFieldError("idProduct", "The 'idProduct' field must not be empty or blank");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(errorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    void whenCustomerIdIsNullVerifyProduct_thenReturns400() throws Exception {
        VerifyProductWishlistRequest request = new VerifyProductWishlistRequest();
        request.setId("1");
        request.setIdProduct("1");

        mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCustomerIdIsBlankVerifyProduct_thenReturns400() throws Exception {
        VerifyProductWishlistRequest request = new VerifyProductWishlistRequest();
        request.setId("1");
        request.setIdProduct("1");
        request.setCustomerId("");

        MvcResult mvcResult = mockMvc.perform(post("/add-product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Payload");
        errorResponse.addFieldError("customerId", "The 'customerId' field must not be empty or blank");
        String actualResponseBody =
                mvcResult.getResponse().getContentAsString();
        String expectedResponseBody =
                objectMapper.writeValueAsString(errorResponse);
        assertThat(actualResponseBody)
                .isEqualToIgnoringWhitespace(expectedResponseBody);
    }
}
