package com.pos.controller;

import com.pos.model.Invoice;
import com.pos.model.MessageResponse;
import com.pos.model.dto.InvoiceDto;
import com.pos.service.InvoiceService;
import com.pos.util.DtoEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final DtoEntityConverter dtoEntityConverter;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, DtoEntityConverter dtoEntityConverter) {
        this.invoiceService = invoiceService;
        this.dtoEntityConverter = dtoEntityConverter;
    }

    @PostMapping("")
    public ResponseEntity<Invoice> postInvoice(@RequestBody InvoiceDto body) {
        Invoice invoice = this.invoiceService.createInvoice(body);
        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> putInvoice(@PathVariable Integer id, @RequestBody InvoiceDto body) {
        //Invoice invoice = this.dtoEntityConverter.convertInvoiceToIdentity(body);
        //invoice.setId(id);
        //invoice = this.invoiceService.updateInvoiceById(invoice, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Integer id) {
        Invoice invoice = this.invoiceService.getById(id);
        return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteInvoice(@PathVariable Integer id) {
        this.invoiceService.removeInvoice(id);
        return new ResponseEntity<>(new MessageResponse("Invoice deleted succesfully"), HttpStatus.OK);
    }
}