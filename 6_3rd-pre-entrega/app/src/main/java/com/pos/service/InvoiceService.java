package com.pos.service;

import com.pos.model.Client;
import com.pos.model.Invoice;
import com.pos.model.InvoiceDetail;
import com.pos.model.dto.InvoiceDtoRequest;
import com.pos.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ClientService clientService;
    private final InvoiceDetailService invoiceDetailService;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            ClientService clientService,
            InvoiceDetailService invoiceDetailService
    ) {
        this.invoiceRepository = invoiceRepository;
        this.clientService = clientService;
        this.invoiceDetailService = invoiceDetailService;
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

    public Invoice createInvoice(InvoiceDtoRequest invoiceData) {
        System.out.println("> Create a invoice");

        Client client = this.clientService.getById(invoiceData.getClientId());
        Invoice invoice = new Invoice();
        invoice.setClient(client);

        try {
            this.invoiceRepository.save(invoice);
            List<InvoiceDetail> invoiceDetails = this.invoiceDetailService.createManyInvoiceDetails(invoice, invoiceData.getProducts());
            this.invoiceRepository.flush();
            invoice.setDetails(invoiceDetails);

            return invoice;
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
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}