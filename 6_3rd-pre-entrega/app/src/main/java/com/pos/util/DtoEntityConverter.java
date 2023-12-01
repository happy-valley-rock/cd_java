package com.pos.util;

import com.pos.model.Client;
import com.pos.model.Invoice;
import com.pos.model.InvoiceDetail;
import com.pos.model.Product;
import com.pos.model.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DtoEntityConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ClientDtoRequest convertClientToDto(Client client) {
        return this.modelMapper.map(client, ClientDtoRequest.class);
    }

    public Client convertClientToIdentity(ClientDtoRequest clientDtoRequest) {
        return this.modelMapper.map(clientDtoRequest, Client.class);
    }

    public ProductDtoRequest convertProductToDto(Product product) {
        return this.modelMapper.map(product, ProductDtoRequest.class);
    }

    public Product convertProductToIdentity(ProductDtoRequest productDtoRequest) {
        return this.modelMapper.map(productDtoRequest, Product.class);
    }

    public InvoiceDtoRequest convertInvoiceToDto(Invoice invoice) {
        return this.modelMapper.map(invoice, InvoiceDtoRequest.class);
    }

    public ProductDtoResponse convertProductToDtoResponse(Product product) {
        return this.modelMapper.map(product, ProductDtoResponse.class);
    }

    public InvoiceDetailDtoResponse convertInvoiceDetailToDtoResponse(InvoiceDetail invoiceDetail) {
        InvoiceDetailDtoResponse invoiceDetailDtoResponse = this.modelMapper.map(invoiceDetail, InvoiceDetailDtoResponse.class);
        invoiceDetailDtoResponse.setProduct(convertProductToDtoResponse(invoiceDetail.getProduct()));
        return invoiceDetailDtoResponse;
    }

    public InvoiceDtoResponse convertInvoiceToDtoResponse(Invoice invoice) {
        List<InvoiceDetailDtoResponse> invoiceDetailDtoResponse = invoice
                .getDetails()
                .stream()
                .map(this::convertInvoiceDetailToDtoResponse)
                .toList();

        InvoiceDtoResponse invoiceDtoResponse = this.modelMapper.map(invoice, InvoiceDtoResponse.class);
        invoiceDtoResponse.setDetails(invoiceDetailDtoResponse);
        return invoiceDtoResponse;
    }
}
