package com.pos.model.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class InvoiceDetailDtoRequest {
    private Integer productId;
    private Integer amount;

    static public List<Integer> getProductIdFromList(List<InvoiceDetailDtoRequest> listInvoiceDetailDtoRequest) {
        return listInvoiceDetailDtoRequest.stream()
                .map(InvoiceDetailDtoRequest::getProductId)
                .collect(Collectors.toList());
    }

    static public HashMap<Integer, Integer> getHashMap(List<InvoiceDetailDtoRequest> listInvoiceDetailDtoRequest) {
        return listInvoiceDetailDtoRequest
                .stream()
                .collect(Collectors.toMap(
                        InvoiceDetailDtoRequest::getProductId,
                        InvoiceDetailDtoRequest::getAmount,
                        (existing, replacement) -> existing, HashMap::new));
    }
}
