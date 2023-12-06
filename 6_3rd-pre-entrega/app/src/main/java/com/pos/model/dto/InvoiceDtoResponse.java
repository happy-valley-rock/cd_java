package com.pos.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pos.model.Client;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoiceDtoResponse {
    private Integer id;
    private String ticket;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private Double totalPrice;
    private Integer totalAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date createdAt;
    private Client client;
    private List<InvoiceDetailDtoResponse> details;
}
