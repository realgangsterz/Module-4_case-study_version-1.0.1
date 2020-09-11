package com.codegym.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "productsizes")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sizeId;
    @NotEmpty
    private String size;

    public ProductSize() {
    }

    public ProductSize(Long sizeId, @NotEmpty String size) {
        this.sizeId = sizeId;
        this.size = size;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
