package com.pos.model.dto;

import lombok.Data;

@Data
public class InvoiceDetailDtoResponse {
    private Integer id;
    private Double price;
    private Integer amount;
    private ProductDtoResponse product;
}

