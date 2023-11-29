package com.pos.service;

import com.pos.model.*;
import com.pos.model.dto.InvoiceDetailsDto;
import com.pos.repository.InvoiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailsService {

    private final InvoiceDetailsRepository invoiceDetailsRepository;
    private final ProductService productService;

    @Autowired
    public InvoiceDetailsService(InvoiceDetailsRepository invoiceDetailsRepository, ProductService productService) {
        this.invoiceDetailsRepository = invoiceDetailsRepository;
        this.productService = productService;
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


    public void createManyInvoiceDetails(Invoice invoice, List<InvoiceDetailsDto> products) {
        Double total = 0d;

        for (int i = 0; i < products.size(); i++) {
            ProductPurchase productPurchase = this.productService.purchaseProduct(
                    products.get(i).getProductId(),
                    products.get(i).getQuantity()
                    );

            Double totalProductPrice = (Double)(productPurchase.getPrice() * products.get(i).getQuantity());
            total += totalProductPrice;

            InvoiceDetailsPurchase invoiceDetailsPurchase = new InvoiceDetailsPurchase();
            invoiceDetailsPurchase.setAmount(products.get(i).getQuantity());
            invoiceDetailsPurchase.setPrice(totalProductPrice);
            invoiceDetailsPurchase.setProduct(productPurchase);

            invoice.getDetails().add(invoiceDetailsPurchase);
        }

        invoice.setTotal(total);
    }
}