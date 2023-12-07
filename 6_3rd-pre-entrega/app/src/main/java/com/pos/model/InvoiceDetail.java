package com.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_details", schema = "pos_simple")
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Double price, Integer amount, Invoice invoice, Product product) {
        this.price = price;
        this.amount = amount;
        this.invoice = invoice;
        this.product = product;
    }
}
