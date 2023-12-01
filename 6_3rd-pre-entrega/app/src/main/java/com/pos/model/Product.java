package com.pos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "products", schema = "pos_simple")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String code;
    private Integer stock;
    private Double sellPrice;
    private Double purchasePrice;

    public static HashMap<Integer, Product> getHashMap(List<Product> listProduct) {
        return listProduct
                .stream()
                .collect(Collectors.toMap(
                        Product::getId,
                        product -> product,
                        (existing, replacement) -> existing, HashMap::new));

    }
}