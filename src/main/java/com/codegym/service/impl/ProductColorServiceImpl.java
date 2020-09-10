package com.codegym.service.impl;

import com.codegym.model.ProductColor;
import com.codegym.repository.ProductColorRepository;
import com.codegym.service.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductColorServiceImpl implements ProductColorService {

    @Autowired
    private ProductColorRepository productColorRepository;
    @Override
    public ProductColor findById(Long id) {
        return productColorRepository.findOne( id );
    }

    @Override
    public Iterable<ProductColor> findAll() {
        return productColorRepository.findAll();
    }
}
