package com.codegym.model.cart;

import com.codegym.model.Product_ProductSize;

public class Cart {
    private Product_ProductSize product_productSize;
    private int quantity;

    public Cart() {
    }

    public Cart(Product_ProductSize product_productSize, int quantity) {
        this.product_productSize = product_productSize;
        this.quantity = quantity;
    }

    public Product_ProductSize getProduct_productSize() {
        return product_productSize;
    }

    public void setProduct_productSize(Product_ProductSize product_productSize) {
        this.product_productSize = product_productSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
