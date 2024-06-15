package com.freedom.wishlist.infrastructure.resource;

import com.freedom.wishlist.config.exception.ErrorResponse;
import com.freedom.wishlist.domain.wishlist.WishlistMapper;
import com.freedom.wishlist.domain.wishlist.request.AddProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.request.RemoveProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.request.VerifyProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.response.AddProductWishlistResponse;
import com.freedom.wishlist.domain.wishlist.response.ProductsWishlistResponse;
import com.freedom.wishlist.domain.wishlist.response.VerifyProductWishlistResponse;
import com.freedom.wishlist.domain.wishlist.useCase.AddProductWishlistUseCase;
import com.freedom.wishlist.domain.wishlist.useCase.GetAllProductsWishlistUseCase;
import com.freedom.wishlist.domain.wishlist.useCase.RemoveProductWishlistUseCase;
import com.freedom.wishlist.domain.wishlist.useCase.VerifyProductExistsWishlistUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Operações da Wishlist")
@RequiredArgsConstructor
@RestController("/wishlist")
public class WishlistController {

    private final WishlistMapper wishlistMapper;
    private final AddProductWishlistUseCase addProductWishlistUseCase;
    private final RemoveProductWishlistUseCase removeProductWishlistUseCase;
    private final GetAllProductsWishlistUseCase getAllProductsWishlistUseCase;
    private final VerifyProductExistsWishlistUseCase verifyProductExistsWishlist;

    @Operation(summary = "Adiciona um produto na Wishlist do cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product adicionado com sucesso.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddProductWishlistResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Product ou wishlist não encontrados.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "422", description = "Wishlist cheia.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/add-product")
    public AddProductWishlistResponse addProductWishlist(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para um produto a uma wishlist.", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddProductWishlistRequest.class)))
            @Validated @RequestBody AddProductWishlistRequest request) {
        var wishlist = wishlistMapper.addProductRequestToEntity(request);
        return addProductWishlistUseCase.add(wishlist);
    }

    @Operation(summary = "Remove um produto da Wishlist do cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Remoção do produto realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Se a id da wishlist ou do produto não forem informadas.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Se o produto não for encontrado na wishlist, ou a wishlist não existe.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping("/remove-product")
    public void removeProductWishlist(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para um produto a uma wishlist.", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RemoveProductWishlistRequest.class)))
            @Validated @RequestBody RemoveProductWishlistRequest request) {
        var wishlist = wishlistMapper.removeProductRequestToEntity(request);
        removeProductWishlistUseCase.remove(wishlist);
    }

    @Operation(summary = "Consulta todos os produtos da Wishlist do cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductsWishlistResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Se id da wishlist não for informada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Se a wishlist não possui produtos.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/findAll-products/{idWishlist}/{idCustomer}")
    public ProductsWishlistResponse getAllProductsByWishlistIdAndCustomerId(
            @Parameter(description = "Id da wishlist.") @PathVariable String idWishlist,
            @Parameter(description = "Id do cliente.") @PathVariable String idCustomer) {
        return getAllProductsWishlistUseCase.getAllProducts(idWishlist, idCustomer);
    }

    @Operation(summary = "Verificar se um determinado produto está na Wishlist do cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VerifyProductWishlistResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Se id do produto ou da wishlist não forem informados.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/verify-products-exists-wishlist")
    public VerifyProductWishlistResponse verifyProductExistsWishlist(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados para um produto a uma wishlist.", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VerifyProductWishlistRequest.class)))
            @Validated @RequestBody VerifyProductWishlistRequest request) {
        var wishlist = wishlistMapper.verifyProductRequestToEntity(request);
        return verifyProductExistsWishlist.verifyProductExists(wishlist);
    }
}
