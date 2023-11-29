package com.billing.model.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String description;
    private String code;
    private Integer stock;
    private Double sellPrice;
    private Double purchasePrice;
}