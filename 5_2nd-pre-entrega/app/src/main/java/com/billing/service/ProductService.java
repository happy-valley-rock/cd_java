package com.billing.service;

import com.billing.model.Product;
import com.billing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Integer productId) {
        System.out.println("> Get product by id " + productId.toString());
        try {
            Optional<Product> optionalProduct = this.productRepository.findById(productId);
            if (optionalProduct.orElse(null) == null) throw new Error("Product does not exist");
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
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
