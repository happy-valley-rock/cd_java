package com.pos.service;

import com.pos.exceptions.BadRequestException;
import com.pos.exceptions.EntityNotFoundException;
import com.pos.model.Product;
import com.pos.model.dto.InvoiceDetailDtoRequest;
import com.pos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Integer productId) {
        System.out.println("> Get product by id " + productId.toString());
        try {
            Optional<Product> optionalProduct = this.productRepository.findById(productId);
            if (optionalProduct.orElse(null) == null) throw new EntityNotFoundException("Product does not exist");
            return optionalProduct.get();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public Product createProduct(Product product) {
        System.out.println("> Create a product");
        try {
            return this.productRepository.save(product);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public Product updateProductById(Product product, Integer productId) {
        System.out.println("> Update product with id " + productId.toString());
        try {
            Product productFounded = this.getById(productId);
            productFounded = product;
            return this.productRepository.save(productFounded);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public void removeProduct(Integer productId) {
        System.out.println("> Remove product with id " + productId.toString());
        try {
            this.getById(productId);
            this.productRepository.deleteById(productId);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public Product checkAvailable(Product product, Integer amount) {
        try {
            Integer stock = product.getStock();

            if (amount > stock) throw new BadRequestException("Insufficient product stock");
            else product.setStock(stock - amount);
            return product;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public List<Product> getAvailableListProducts(List<InvoiceDetailDtoRequest> listInvoiceDetailDtoRequest) {
        HashMap<Integer, Integer> hashMapInvoiceDetailsDto = InvoiceDetailDtoRequest.getHashMap(listInvoiceDetailDtoRequest);
        List<Integer> listIds = InvoiceDetailDtoRequest.getProductIdFromList(listInvoiceDetailDtoRequest);
        List<Product> listProduct = new ArrayList<Product>();

        try {
            listProduct = this.productRepository.findAllById(listIds);
            for (int i = 0; i < listProduct.size(); i++) {
                Product product = listProduct.get(i);
                this.checkAvailable(product, hashMapInvoiceDetailsDto.get(product.getId()));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }

        return listProduct;
    }

    public void updateManyProducts(Iterable<Product> products) {
        try {
            this.productRepository.saveAll(products);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public List<Product> getList() {
        System.out.println("> Get product list");
        try {
            List<Product> productList = this.productRepository.findAll();
            return productList;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
