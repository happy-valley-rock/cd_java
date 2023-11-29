package com.pos.model;

import lombok.Data;

@Data
public class ProductPurchase {
    private Integer productId;
    private String description;
    private String code;
    private Double price;

    public ProductPurchase() {}
    public ProductPurchase(Product product) {
        this.productId = product.getId();
        this.description = product.getDescription();
        this.code = product.getCode();
        this.price = product.getSellPrice();
    }
}
