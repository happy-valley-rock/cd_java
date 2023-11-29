package com.billing.service;

import com.billing.model.InvoiceDetails;
import com.billing.repository.InvoiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceDetailsService {

    private final InvoiceDetailsRepository invoiceDetailsRepository;

    @Autowired
    public InvoiceDetailsService(InvoiceDetailsRepository invoiceDetailsRepository) {
        this.invoiceDetailsRepository = invoiceDetailsRepository;
    }

    public InvoiceDetails getById(Integer invoiceDetailsId) {
        System.out.println("> Get invoice details by id " + invoiceDetailsId.toString());
        Optional<InvoiceDetails> optionalInvoiceDetails = this.invoiceDetailsRepository.findById(invoiceDetailsId);
        return optionalInvoiceDetails.orElse(null);
    }

    public void createInvoiceDetails(InvoiceDetails invoiceDetails) {
        System.out.println("> Create a invoice details");
        this.invoiceDetailsRepository.save(invoiceDetails);
    }

    public void updateInvoiceDetailsById(InvoiceDetails invoiceDetails, Integer invoiceDetailsId) {
        System.out.println("> Update invoice details with id " + invoiceDetailsId.toString());

        InvoiceDetails invoiceDetailsFounded = this.getById(invoiceDetailsId);
        if (invoiceDetailsFounded == null) throw new Error("Invoice details does not exist");

        invoiceDetailsFounded = invoiceDetails;
        this.invoiceDetailsRepository.save(invoiceDetailsFounded);
    }

    public void removeInvoiceDetails(Integer invoiceDetailsId) {
        System.out.println("> Remove invoice details with id " + invoiceDetailsId.toString());
        this.invoiceDetailsRepository.deleteById(invoiceDetailsId);
    }
}