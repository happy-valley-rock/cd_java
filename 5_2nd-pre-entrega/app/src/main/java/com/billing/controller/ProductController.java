package com.billing.controller;

import com.billing.model.Product;
import com.billing.model.MessageResponse;
import com.billing.model.dto.ProductDto;
import com.billing.service.ProductService;
import com.billing.util.DtoEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    private final ProductService productService;

    private final DtoEntityConverter dtoEntityConverter;

    @Autowired
    public ProductController(ProductService productService, DtoEntityConverter dtoEntityConverter) {
        this.productService = productService;
        this.dtoEntityConverter = dtoEntityConverter;
    }

    @PostMapping("")
    public ResponseEntity<Product> postProduct(@RequestBody ProductDto body) {
        Product product = this.dtoEntityConverter.convertProductToIdentity(body);
        product = this.productService.createProduct(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable Integer id, @RequestBody ProductDto body) {
        Product product = this.dtoEntityConverter.convertProductToIdentity(body);
        product.setId(id);
        product = this.productService.updateProductById(product, id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        Product product = this.productService.getById(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable Integer id) {
        this.productService.removeProduct(id);
        return new ResponseEntity<>(new MessageResponse("Product deleted succesfully"), HttpStatus.OK);
    }
}