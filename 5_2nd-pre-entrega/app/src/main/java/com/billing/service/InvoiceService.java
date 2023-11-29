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
        try {
            Optional<Invoice> optionalInvoice = this.invoiceRepository.findById(invoiceId);
            if (optionalInvoice.orElse(null) == null) throw new Error("Invoice does not exist");
            return optionalInvoice.get();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public Invoice createInvoice(Invoice invoice) {
        System.out.println("> Create a invoice");
        try {
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