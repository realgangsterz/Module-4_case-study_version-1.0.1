package com.codegym.service;

import com.codegym.model.Product_ProductSize;
import com.codegym.model.product.Category;
import com.codegym.model.product.Product;
import com.codegym.model.product.ProductSize;


public interface Product_ProductSizeService {
    Iterable<Product_ProductSize> findAllByProduct(Product product);
    Product_ProductSize findAllByProductAndProductSize(Product product, ProductSize productSize);
}
