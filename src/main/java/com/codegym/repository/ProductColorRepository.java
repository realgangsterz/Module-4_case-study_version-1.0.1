package com.codegym.repository;

import com.codegym.model.Category;
import com.codegym.model.ProductColor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductColorRepository extends PagingAndSortingRepository<ProductColor,Long> {
}
