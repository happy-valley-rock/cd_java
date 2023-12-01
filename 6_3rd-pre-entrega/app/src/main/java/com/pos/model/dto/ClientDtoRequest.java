package com.pos.model.dto;

import lombok.Data;

@Data
public class ClientDtoRequest {
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String documentType;
}
