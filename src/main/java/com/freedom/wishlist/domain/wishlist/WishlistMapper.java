package com.freedom.wishlist.domain.wishlist;

import com.freedom.wishlist.domain.wishlist.request.AddProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.request.RemoveProductWishlistRequest;
import com.freedom.wishlist.domain.wishlist.request.VerifyProductWishlistRequest;
import com.freedom.wishlist.infrastructure.entity.WishlistModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    WishlistModel wishlistToWishlistModel(Wishlist wishlist);

    Wishlist wishlistModelToWishlist(WishlistModel wishlist);

    @Mapping(expression = "java(Set.of(request.getIdProduct()))", target = "idProducts")
    Wishlist addProductRequestToEntity(AddProductWishlistRequest request);

    @Mapping(expression = "java(Set.of(request.getIdProduct()))", target = "idProducts")
    Wishlist removeProductRequestToEntity(RemoveProductWishlistRequest request);

    @Mapping(expression = "java(Set.of(request.getIdProduct()))", target = "idProducts")
    Wishlist verifyProductRequestToEntity(VerifyProductWishlistRequest request);
}
