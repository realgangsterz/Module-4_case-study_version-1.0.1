package com.codegym.repository;

import com.codegym.model.product.ProductSize;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductSizeRepository extends PagingAndSortingRepository<ProductSize,Long> {
    ProductSize findBySize(String size);
}
