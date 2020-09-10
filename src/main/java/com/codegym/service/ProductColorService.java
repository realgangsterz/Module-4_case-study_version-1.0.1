package com.codegym.service;


import com.codegym.model.ProductColor;

public interface ProductColorService {
    ProductColor findById(Long id);
    Iterable<ProductColor> findAll();
}
