package com.pos.service;

import com.pos.model.Invoice;
import com.pos.model.InvoiceDetail;
import com.pos.model.Product;
import com.pos.model.dto.InvoiceDetailDtoRequest;
import com.pos.repository.InvoiceDetailRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailService {

    private final InvoiceDetailRepository invoiceDetailRepository;
    private final ProductService productService;

    public InvoiceDetailService(InvoiceDetailRepository invoiceDetailRepository, ProductService productService) {
        this.invoiceDetailRepository = invoiceDetailRepository;
        this.productService = productService;
    }

    public InvoiceDetail getById(Integer invoiceDetailId) {
        System.out.println("> Get invoice details by id " + invoiceDetailId.toString());
        try {
            Optional<InvoiceDetail> optionalInvoiceDetail = this.invoiceDetailRepository.findById(invoiceDetailId);
            if (optionalInvoiceDetail.orElse(null) == null) throw new Error("Client does not exist");
            return optionalInvoiceDetail.get();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public List<InvoiceDetail> createManyInvoiceDetails(Invoice invoice, List<InvoiceDetailDtoRequest> purchaseProducts) {
        List<Product> listProducts = this.productService.getAvailableListProducts(purchaseProducts);
        HashMap<Integer, Product> hashMapProduct = Product.getHashMap(listProducts);
        List<InvoiceDetail> listInvoiceDetails = new ArrayList<InvoiceDetail>();
        Double totalPrice = 0d;
        Integer totalAmount = 0;

        for (int i = 0; i < purchaseProducts.size(); i++) {
            Product product = hashMapProduct.get(purchaseProducts.get(i).getProductId());
            Integer amount = purchaseProducts.get(i).getAmount();
            Double price = product.getSellPrice() * amount;

            listInvoiceDetails.add(new InvoiceDetail(price, amount, invoice, product));
            totalPrice += price;
            totalAmount += amount;
        }

        this.invoiceDetailRepository.saveAll(listInvoiceDetails);
        this.productService.updateManyProducts(listProducts);
        invoice.setTotalPrice(totalPrice);
        invoice.setTotalAmount(totalAmount);
        return listInvoiceDetails;
    }
}