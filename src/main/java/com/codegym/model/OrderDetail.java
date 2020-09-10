package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long detailId;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Long detailId, Order order, Product product) {
        this.detailId = detailId;
        this.order = order;
        this.product = product;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
