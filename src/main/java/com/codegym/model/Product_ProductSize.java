package com.codegym.model;

import javax.persistence.*;
@Entity
@Table(name = "product_productsize")
public class Product_ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_productSizeID;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private ProductSize productSize;

    public Product_ProductSize() {
    }

    public Product_ProductSize(Long product_productSizeID, Product product, ProductSize productSize) {
        this.product_productSizeID = product_productSizeID;
        this.product = product;
        this.productSize = productSize;
    }

    public Long getProduct_productSizeID() {
        return product_productSizeID;
    }

    public void setProduct_productSizeID(Long product_productSizeID) {
        this.product_productSizeID = product_productSizeID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }
}
