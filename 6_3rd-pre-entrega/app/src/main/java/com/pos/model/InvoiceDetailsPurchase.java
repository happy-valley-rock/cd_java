package com.pos.model;

import lombok.Data;

@Data
public class InvoiceDetailsPurchase {
    private Integer id;
    private Double price;
    private Integer amount;
    private ProductPurchase product;

    public InvoiceDetailsPurchase() {}

    public InvoiceDetailsPurchase(InvoiceDetails invoiceDetails) {
        this.id = invoiceDetails.getId();
        this.price = invoiceDetails.getPrice();
        this.amount = invoiceDetails.getAmount();
        this.product = new ProductPurchase(invoiceDetails.getProduct());
    }
}
