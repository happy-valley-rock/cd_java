package com.billing.util;

import com.billing.model.Client;
import com.billing.model.Invoice;
import com.billing.model.Product;
import com.billing.model.dto.ClientDto;
import com.billing.model.dto.InvoiceDto;
import com.billing.model.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoEntityConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ClientDto convertClientToDto(Client client) {
        ClientDto clientDto = this.modelMapper.map(client, ClientDto.class);
        return clientDto;
    }

    public Client convertClientToIdentity(ClientDto clientDto) {
        Client client = this.modelMapper.map(clientDto, Client.class);
        return  client;
    }

    public ProductDto convertProductToDto(Product product) {
        ProductDto productDto = this.modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public Product convertProductToIdentity(ProductDto productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        return product;
    }

    public InvoiceDto convertInvoiceToDto(Invoice invoice) {
        InvoiceDto invoiceDto = this.modelMapper.map(invoice, InvoiceDto.class);
        return invoiceDto;
    }

    public Invoice convertInvoiceToIdentity(InvoiceDto invoiceDto) {
        Invoice invoice = this.modelMapper.map(invoiceDto, Invoice.class);
        return invoice;
    }
}
