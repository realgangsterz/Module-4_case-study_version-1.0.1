package com.codegym.repository;

import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository  extends PagingAndSortingRepository <Product,Long> {
    Page<Product> findAllByProductNameContaining(String productName, Pageable pageable);
    Page<Product> findAllByCategory(Category category,Pageable pageable);
}
