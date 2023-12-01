package com.pos.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDtoRequest {
    private Integer clientId;
    private List<InvoiceDetailDtoRequest> products;
}
