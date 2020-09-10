package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.ProductSize;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductSizeRepository extends PagingAndSortingRepository<ProductSize,Long> {
}
