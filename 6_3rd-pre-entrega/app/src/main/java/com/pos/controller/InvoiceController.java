package com.pos.controller;

import com.pos.model.MessageResponse;
import com.pos.model.dto.InvoiceDtoRequest;
import com.pos.model.dto.InvoiceDtoResponse;
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
    public ResponseEntity<InvoiceDtoResponse> postInvoice(@RequestBody InvoiceDtoRequest body) {
        InvoiceDtoResponse invoice = this.dtoEntityConverter.convertInvoiceToDtoResponse(this.invoiceService.createInvoice(body));
        return new ResponseEntity<InvoiceDtoResponse>(invoice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDtoResponse> getInvoice(@PathVariable Integer id) {
        InvoiceDtoResponse invoice = this.dtoEntityConverter.convertInvoiceToDtoResponse(this.invoiceService.getById(id));
        return new ResponseEntity<InvoiceDtoResponse>(invoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteInvoice(@PathVariable Integer id) {
        this.invoiceService.removeInvoice(id);
        return new ResponseEntity<>(new MessageResponse("Invoice deleted succesfully"), HttpStatus.OK);
    }
}