package com.pos.model.dto;

import com.pos.model.Client;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoiceDtoResponse {
    private Integer id;
    private Double total;
    private Date createdAt;
    private Client client;
    private List<InvoiceDetailDtoResponse> details;
}
