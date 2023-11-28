package com.billing.controller;

import com.billing.model.InvoiceDetails;
import com.billing.service.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/invoice-details")
public class InvoiceDetailsController {

    private final InvoiceDetailsService invoiceDetailsService;

    @Autowired
    public InvoiceDetailsController(InvoiceDetailsService invoiceDetailsService) {
        this.invoiceDetailsService = invoiceDetailsService;
    }

    @PostMapping("")
    public ResponseEntity<?> postInvoiceDetails(@RequestBody InvoiceDetails body) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putInvoiceDetails(@PathVariable Integer id, @RequestBody InvoiceDetails body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceDetails(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoiceDetails(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}