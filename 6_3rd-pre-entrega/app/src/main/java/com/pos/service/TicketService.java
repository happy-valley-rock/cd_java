package com.pos.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
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
        this.createFooter(invoice, document, font);

        document.close();
        return outputStream;
    }

    private void createHeader(Invoice invoice, Document document, Font font) throws DocumentException {
        Client client = invoice.getClient();
        String name = client.getFirstName().toUpperCase() + " " + client.getLastName().toUpperCase();
        String documentType = "DOCUMENT " + client.getDocumentType().toUpperCase();
        String documentNumber = "DOCUMENT Nmber : " + client.getDocumentNumber();
        String invoiceId = "Nmber. T. : " + invoice.getId();
        String date = "Date : " + invoice.getCreatedAt().toString();

        Paragraph paragraphName = new Paragraph(overlappedText(name), font);
        Paragraph paragraphDocumentNumber = new Paragraph(overlappedText(documentNumber), font);
        Paragraph paragraphDocumentType = new Paragraph(overlappedText(documentType), font);
        Paragraph paragraphSubtitle = new Paragraph(overlappedText("FINAL CONSUMER"), font);
        Paragraph paragraphInvoiceId = new Paragraph(overlappedText(invoiceId), font);
        Paragraph paragraphDate = new Paragraph(overlappedText(date), font);

        document.add(paragraphName);
        document.add(paragraphDocumentType);
        document.add(paragraphDocumentNumber);
        document.add(paragraphSubtitle);
        document.add(paragraphInvoiceId);
        document.add(paragraphDate);
    }

    private void createBody(Invoice invoice, Document document, Font font) throws DocumentException {
        List<InvoiceDetail> invoiceDetailList = invoice.getDetails();
        String header = overlappedText("DESCRIPTION");
        header = overlappedText("(U) PRICE", Alignment.END, header);

        Paragraph paragraphSeparator = new Paragraph(overlappedText("="), font);
        Paragraph paragraphHeader = new Paragraph(header, font);

        document.add(paragraphSeparator);
        document.add(paragraphHeader);
        document.add(paragraphSeparator);

        for (int i = 0; i < invoiceDetailList.size(); i++) {
            InvoiceDetail invoiceDetail = invoiceDetailList.get(i);

            String amount = invoiceDetail.getAmount().toString();
            Double unitPrice = (invoiceDetail.getPrice() / invoiceDetail.getAmount());
            String price = " (" + unitPrice.toString() + ") " + invoiceDetail.getPrice().toString();
            String description = invoiceDetail.getProduct().getDescription().toUpperCase();
            String detail = overlappedText(description + " x" + amount);
            detail = overlappedText(price, Alignment.END, detail);

            Paragraph paragraphDetail = new Paragraph(overlappedText(detail), font);
            document.add(paragraphDetail);
        }
    }

    public void createFooter(Invoice invoice, Document document, Font font) throws DocumentException {
        Font fontBold = FontFactory.getFont(FontFactory.COURIER_BOLD, 20);
        String total = "TOTAL : " + invoice.getTotalPrice().toString();
        Paragraph separator = new Paragraph(overlappedText("."), font);
        Paragraph title = new Paragraph(total, fontBold);
        document.add(separator);
        document.add(title);
    }

    private String overlappedText(String text) {
        if (text.length() == 1) return overlappedText("", Alignment.START, text);
        return overlappedText(text, Alignment.START);
    }

    private String overlappedText(String text, Alignment alignment) {
        String template = " ";
        return  overlappedText(text, alignment, template);
    }

    private String overlappedText(String text, Alignment alignment, String template) {
        int repeatCount = 48;
        return  overlappedText(text, alignment, template, repeatCount);
    }

    private String overlappedText(String text, Alignment alignment, String template, int repeatCount) {
        if (template.length() == 1) {
            char character = template.charAt(0);
            template = new String(new char[repeatCount]).replace('\0', character);
        }

        if (text.length() > repeatCount) text = text.substring(0, repeatCount);

        int templateLength = template.length();
        int textLength = text.length();

        if (alignment == Alignment.START) return text + template.substring(textLength, templateLength);
        else if (alignment == Alignment.END) return template.substring(0, templateLength - textLength) + text;
        else throw new IllegalArgumentException("Invalid alignment");
    }

    public enum Alignment {
        START,
        END
    }
}
