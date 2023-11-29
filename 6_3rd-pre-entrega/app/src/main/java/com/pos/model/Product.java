package com.pos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products", schema = "billing_project")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String code;
    private Integer stock;
    private Double sellPrice;
    private Double purchasePrice;

    @Override
    public String toString() { return ""; }
}