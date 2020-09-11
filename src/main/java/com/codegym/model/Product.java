package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotEmpty
    private String productName;

    private Double productPrice;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @NotEmpty
    private String discount;

    @NotEmpty
    private String productPhoto;

    @NotEmpty
    private String amount;

    private Long shopping;

    @NotEmpty
    private String description;



    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ProductColor productColor;

    public Product() {
    }



    public Product(Long productId, @NotEmpty String productName, Double productPrice, Producer producer, @NotEmpty String discount, @NotEmpty String productPhoto, @NotEmpty String amount, Long shopping, @NotEmpty String description, Category category, ProductColor productColor) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.producer = producer;
        this.discount = discount;
        this.productPhoto = productPhoto;
        this.amount = amount;
        this.shopping = shopping;
        this.description = description;
        this.category = category;
        this.productColor = productColor;

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getShopping() {
        return shopping;
    }

    public void setShopping(Long shopping) {
        this.shopping = shopping;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
