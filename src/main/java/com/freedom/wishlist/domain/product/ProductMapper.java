package com.freedom.wishlist.domain.product;

import com.freedom.wishlist.infrastructure.entity.ProductModel;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductModel productToProductModel(Product product);

    Product productModelToProduct(ProductModel productModel);

    Set<Product> setProductModelToSetProduct(Set<ProductModel> productModel);

}
