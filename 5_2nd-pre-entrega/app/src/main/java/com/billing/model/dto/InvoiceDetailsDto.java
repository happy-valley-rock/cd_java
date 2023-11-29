package com.billing.model.dto;

import com.billing.model.Invoice;
import com.billing.model.Product;
import lombok.Data;

@Data
public class InvoiceDetailsDto {
    private Double price;
    private Integer amount;
    private Invoice invoice;
    private Product product;
}
