package com.pos.controller;

import com.itextpdf.text.DocumentException;
import com.pos.model.Invoice;
import com.pos.model.MessageResponse;
import com.pos.model.dto.InvoiceDtoRequest;
import com.pos.model.dto.InvoiceDtoResponse;
import com.pos.service.InvoiceService;
import com.pos.service.TicketService;
import com.pos.util.DtoEntityConverter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping(path = "/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final DtoEntityConverter dtoEntityConverter;
    private final TicketService ticketService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, DtoEntityConverter dtoEntityConverter, TicketService ticketService) {
        this.invoiceService = invoiceService;
        this.dtoEntityConverter = dtoEntityConverter;
        this.ticketService = ticketService;
    }

    @Operation(summary = "Create a invoice")
    @PostMapping("")
    public ResponseEntity<InvoiceDtoResponse> postInvoice(@RequestBody InvoiceDtoRequest body) {
        InvoiceDtoResponse invoice = this.dtoEntityConverter.convertInvoiceToDtoResponse(this.invoiceService.createInvoice(body));
        String ticket = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/invoice/ticket").build().toUriString() + "/" + invoice.getId();
        invoice.setTicket(ticket);
        return new ResponseEntity<InvoiceDtoResponse>(invoice, HttpStatus.CREATED);
    }

    @Operation(summary = "Read a invoice")
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDtoResponse> getInvoice(@PathVariable Integer id) {
        InvoiceDtoResponse invoice = this.dtoEntityConverter.convertInvoiceToDtoResponse(this.invoiceService.getById(id));
        String ticket = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/invoice/ticket").build().toUriString() + "/" + invoice.getId();
        invoice.setTicket(ticket);
        return new ResponseEntity<InvoiceDtoResponse>(invoice, HttpStatus.OK);
    }

    @Operation(summary = "Remove a invoice")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteInvoice(@PathVariable Integer id) {
        this.invoiceService.removeInvoice(id);
        return new ResponseEntity<>(new MessageResponse("Invoice deleted succesfully"), HttpStatus.OK);
    }

    @Operation(summary = "Generate a PDF ticket")
    @GetMapping("/ticket/{invoiceId}")
    public ResponseEntity<byte[]> getTicket(@PathVariable Integer invoiceId) throws DocumentException {
        Invoice invoice = this.invoiceService.getById(invoiceId);
        ByteArrayOutputStream pdfStream = this.ticketService.generatePdfStream(invoice);
        String fileName = invoice.getId() + "-" + invoice.getCreatedAt().getTime();
        String headerContent = "attachment; filename=" + fileName + ".pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, headerContent);
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
    }
}