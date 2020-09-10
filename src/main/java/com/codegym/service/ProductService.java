package com.codegym.service;

import com.codegym.model.Category;
import com.codegym.model.Producer;
import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface ProductService {
    Page<Product> findAllByProductNameContaining(String productName, Pageable pageable);

    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByProducer(Producer producer);

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    void save(Product product);

    void remove(Long id);

}
