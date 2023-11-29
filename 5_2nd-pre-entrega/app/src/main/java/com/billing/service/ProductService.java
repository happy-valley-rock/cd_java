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
        Optional<Product> optionalProduct = this.productRepository.findById(productId);
        return optionalProduct.orElse(null);
    }

    public void createProduct(Product product) {
        System.out.println("> Create a product");
        this.productRepository.save(product);
    }

    public void updateProductById(Product product, Integer productId) {
        System.out.println("> Update product with id " + productId.toString());

        Product productFounded = this.getById(productId);
        if (productFounded == null) throw new Error("Product does not exist");

        productFounded = product;
        this.productRepository.save(productFounded);
    }
}
