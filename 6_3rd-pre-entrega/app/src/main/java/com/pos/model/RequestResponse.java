package com.pos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestResponse {
    private String message;
    public RequestResponse(String message) {
        this.message = message;
    }
}
