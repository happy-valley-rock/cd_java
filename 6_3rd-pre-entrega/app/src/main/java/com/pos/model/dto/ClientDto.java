package com.pos.model.dto;

import lombok.Data;

@Data
public class ClientDto {
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String documentType;
}
