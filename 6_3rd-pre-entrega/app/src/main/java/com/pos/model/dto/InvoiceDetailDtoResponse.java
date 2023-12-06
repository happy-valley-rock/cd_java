package com.pos.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class InvoiceDetailDtoResponse {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private Double price;
    private Integer amount;
    private ProductDtoResponse product;
}