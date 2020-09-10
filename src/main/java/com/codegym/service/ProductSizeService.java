package com.codegym.service;


import com.codegym.model.ProductSize;

public interface ProductSizeService {
    ProductSize findById(Long id);
    Iterable<ProductSize> findAll();
}
