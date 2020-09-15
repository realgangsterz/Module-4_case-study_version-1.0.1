package com.codegym.service.impl.product;

import com.codegym.model.product.ProductSize;
import com.codegym.repository.ProductSizeRepository;
import com.codegym.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductSizeServiceImpl implements ProductSizeService {
    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Override
    public ProductSize findById(Long id) {
        return productSizeRepository.findOne( id );
    }

    @Override
    public Iterable<ProductSize> findAll() {
        return productSizeRepository.findAll();
    }

    @Override
    public ProductSize findBySize(String size) {
        return productSizeRepository.findBySize(size);
    }
}
