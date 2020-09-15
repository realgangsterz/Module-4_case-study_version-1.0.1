package com.codegym.service.impl.product;

import com.codegym.model.Product_ProductSize;
import com.codegym.model.product.Product;

import com.codegym.model.product.ProductSize;
import com.codegym.repository.Product_ProductSizeRepository;
import com.codegym.service.Product_ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;

public class Product_ProductSizeServiceImpl implements Product_ProductSizeService {

    @Autowired
    private Product_ProductSizeRepository product_productSizeRepository;
    @Override
    public Iterable<Product_ProductSize> findAllByProduct(Product product) {
        return product_productSizeRepository.findAllByProduct( product );
    }

    @Override
    public Product_ProductSize findAllByProductAndProductSize(Product product, ProductSize productSize) {
        return product_productSizeRepository.findAllByProductAndProductSize(product,productSize);
    }
}
