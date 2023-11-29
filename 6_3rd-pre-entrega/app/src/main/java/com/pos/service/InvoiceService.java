package com.pos.service;

import com.pos.model.Client;
import com.pos.model.Invoice;
import com.pos.model.dto.InvoiceDto;
import com.pos.repository.InvoiceRepository;
import com.pos.util.DtoEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ClientService clientService;
    private final InvoiceDetailsService invoiceDetailsService;
    private final DtoEntityConverter dtoEntityConverter;

    @Autowired
    public InvoiceService(
            InvoiceRepository invoiceRepository,
            ClientService clientService,
            InvoiceDetailsService invoiceDetailsService,
            DtoEntityConverter dtoEntityConverter
    ) {
        this.invoiceRepository = invoiceRepository;
        this.clientService = clientService;
        this.invoiceDetailsService = invoiceDetailsService;
        this.dtoEntityConverter = dtoEntityConverter;
    }

    public Invoice getById(Integer invoiceId) {
        System.out.println("> Get invoice by id " + invoiceId.toString());
        try {
            Optional<Invoice> optionalInvoice = this.invoiceRepository.findById(invoiceId);
            if (optionalInvoice.orElse(null) == null) throw new Error("Invoice does not exist");
            return optionalInvoice.get();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public Invoice createInvoice(InvoiceDto invoiceData) {
        System.out.println("> Create a invoice");
        try {
            Invoice invoice = new Invoice();
            Client client = this.clientService.getById(invoiceData.getClientId());
            invoice.setClient(client);
            this.invoiceDetailsService.createManyInvoiceDetails(invoice, invoiceData.getProducts());
            return this.invoiceRepository.save(invoice);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public Invoice updateInvoiceById(Invoice invoice, Integer invoiceId) {
        System.out.println("> Update invoice with id " + invoiceId.toString());
        try {
            Invoice invoiceFounded = this.getById(invoiceId);
            invoiceFounded = invoice;
            return this.invoiceRepository.save(invoiceFounded);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public void removeInvoice(Integer invoiceId) {
        System.out.println("> Remove invoice with id " + invoiceId.toString());
        try {
            this.getById(invoiceId);
            this.invoiceRepository.deleteById(invoiceId);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}