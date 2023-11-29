package com.billing.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageResponse {
    private Date timestamp = new Date();
    private Integer status = 200;
    private String description;

    public MessageResponse(String description) {
        this.description = description;
    }
    public MessageResponse(String description, Integer status) {
        this.description = description;
        this.status = status;
    }
}
