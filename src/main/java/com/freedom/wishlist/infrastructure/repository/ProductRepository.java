package com.freedom.wishlist.infrastructure.repository;

import com.freedom.wishlist.domain.product.Product;
import com.freedom.wishlist.domain.product.ProductMapper;
import com.freedom.wishlist.domain.product.repository.ProductRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements ProductRepositoryInterface {

    private final ProductDao productDao;
    private final ProductMapper productMapper;

    @Override
    public Optional<Product> findById(String productId) {
        return productDao.findById(productId).map(productMapper::productModelToProduct);
    }

    @Override
    public Set<Product> findByIdIn(Set<String> productIds) {
        return productMapper.setProductModelToSetProduct(productDao.findByIdIn(productIds));
    }
}
