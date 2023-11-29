package com.billing.model.dto;

import com.billing.model.Client;
import com.billing.model.InvoiceDetails;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoiceDto {
    private Double total;
    private Date createdAt;
    private Client client;
    private List<InvoiceDetails> invoiceDetails;
}
