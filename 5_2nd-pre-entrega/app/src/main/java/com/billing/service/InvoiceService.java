package com.billing.service;

import com.billing.model.Invoice;
import com.billing.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice getById(Integer invoiceId) {
        System.out.println("> Get invoice by id " + invoiceId.toString());
        Optional<Invoice> optionalInvoice = this.invoiceRepository.findById(invoiceId);
        return optionalInvoice.orElse(null);
    }

    public void createInvoice(Invoice invoice) {
        System.out.println("> Create a invoice");
        this.invoiceRepository.save(invoice);
    }

    public void updateInvoiceById(Invoice invoice, Integer invoiceId) {
        System.out.println("> Update invoice with id " + invoiceId.toString());

        Invoice invoiceFounded = this.getById(invoiceId);
        if (invoiceFounded == null) throw new Error("Invoice does not exist");

        invoiceFounded = invoice;
        this.invoiceRepository.save(invoiceFounded);
    }
}