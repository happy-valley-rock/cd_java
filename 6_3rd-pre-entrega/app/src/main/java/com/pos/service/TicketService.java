package com.pos.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.pos.model.Client;
import com.pos.model.Invoice;
import com.pos.model.InvoiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private InvoiceService invoiceService;

    public ByteArrayOutputStream generatePdfStream(Invoice invoice) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 18);

        this.createHeader(invoice, document, font);
        this.createBody(invoice, document, font);
        this.createFooter(invoice, document);

        document.close();
        return outputStream;
    }

    private void createHeader(Invoice invoice, Document document, Font font) {
        Client client = invoice.getClient();
        String name = client.getFirstName().toUpperCase() + " " + client.getLastName().toUpperCase();
        String documentType = "DOCUMENT " + client.getDocumentType().toUpperCase();
        String documentNumber = "DOCUMENT Nmber : " + client.getDocumentNumber();
        String invoiceId = "Nmber. T. : " + invoice.getId();
        String date = "Date : " + invoice.getCreatedAt().toString();

        Paragraph paragraphName = new Paragraph(name, font);
        Paragraph paragraphDocumentNumber = new Paragraph(documentNumber, font);
        Paragraph paragraphDocumentType = new Paragraph(documentType, font);
        Paragraph paragraphSubtitle = new Paragraph("FINAL CONSUMER", font);
        Paragraph paragraphInvoiceId = new Paragraph(invoiceId, font);
        Paragraph paragraphDate = new Paragraph(date, font);

        document.add(paragraphName);
        document.add(paragraphDocumentType);
        document.add(paragraphDocumentNumber);
        document.add(paragraphSubtitle);
        document.add(paragraphInvoiceId);
        document.add(paragraphDate);
    }

    private void createBody(Invoice invoice, Document document, Font font) {
        List<InvoiceDetail> invoiceDetailList = invoice.getDetails();
        Paragraph separator = new Paragraph("================================================", font);
        Paragraph header = new Paragraph("DESCRIPTION                                PRICE", font);
        separator.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(separator);
        document.add(header);
        document.add(separator);

        for (int i = 0; i < invoiceDetailList.size(); i++) {
            InvoiceDetail invoiceDetail = invoiceDetailList.get(i);

            String amount = invoiceDetail.getAmount().toString();
            String price = invoiceDetail.getPrice().toString();
            String description = invoiceDetail.getProduct().getDescription().toUpperCase();
            String detail = description + " x" + amount + " " + price;
            Paragraph paragraphDetail = new Paragraph(detail, font);
            document.add(paragraphDetail);
        }
    }

    public void createFooter(Invoice invoice, Document document) {
        Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 20);
        String total = "TOTAL : " + invoice.getTotalPrice().toString();
        Paragraph separator = new Paragraph("...........................................", font);
        Paragraph title = new Paragraph(total, font);
        document.add(separator);
        document.add(title);
    }
}
