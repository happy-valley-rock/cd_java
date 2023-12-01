package com.pos.model.dto;

import lombok.Data;

@Data
public class ProductDtoRequest {
    private String description;
    private String code;
    private Integer stock;
    private Double sellPrice;
    private Double purchasePrice;
}
