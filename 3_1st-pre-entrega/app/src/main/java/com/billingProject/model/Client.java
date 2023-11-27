package com.billingProject.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String dni;



}
