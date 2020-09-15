package com.codegym.repository;

import com.codegym.model.Product_ProductSize;
import com.codegym.model.product.Product;
import com.codegym.model.product.ProductSize;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Product_ProductSizeRepository extends PagingAndSortingRepository<Product_ProductSize,Long> {
    Iterable<Product_ProductSize> findAllByProduct(Product product);

    Product_ProductSize findAllByProductAndProductSize(Product product, ProductSize productSize);
}
