package com.billing.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clients", schema = "billing_project")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "document_type")
    private String documentType;
}
