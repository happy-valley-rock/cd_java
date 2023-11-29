package com.pos.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDto {
    private Integer clientId;
    private List<InvoiceDetailsDto> products;
}
