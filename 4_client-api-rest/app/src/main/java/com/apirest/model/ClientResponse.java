package com.apirest.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientResponse {
    private String firstName;
    private String lastName;
    private Integer age;

    public ClientResponse(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
