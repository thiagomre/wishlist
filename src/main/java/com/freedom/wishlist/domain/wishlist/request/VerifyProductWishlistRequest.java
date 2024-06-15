package com.freedom.wishlist.domain.wishlist.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyProductWishlistRequest {

    @NotNull(message = "The 'id' field is mandatory")
    @NotBlank(message = "The 'id' field must not be empty or blank")
    private String id;
    @NotNull(message = "The 'customerId' field is mandatory")
    @NotBlank(message = "The 'customerId' field must not be empty or blank")
    private String customerId;
    @NotNull(message = "The 'idProduct' field is mandatory")
    @NotBlank(message = "The 'idProduct' field must not be empty or blank")
    private String idProduct;

}
