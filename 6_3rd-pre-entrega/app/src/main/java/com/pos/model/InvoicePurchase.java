package com.pos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class InvoicePurchase {
    private Integer id;
    private Double total = 0d;
    private Date createdAt = new Date();
    private Client client;
    private List<InvoiceDetailsPurchase> details = new ArrayList<InvoiceDetailsPurchase>();
    @Override
    public String toString() { return ""; }
}
