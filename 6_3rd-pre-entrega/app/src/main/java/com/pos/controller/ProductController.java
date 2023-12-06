package com.pos.controller;

import com.pos.model.MessageResponse;
import com.pos.model.Product;
import com.pos.model.dto.ProductDtoRequest;
import com.pos.service.ProductService;
import com.pos.util.DtoEntityConverter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Create a product")
    @PostMapping("")
    public ResponseEntity<Product> postProduct(@RequestBody ProductDtoRequest body) {
        Product product = this.dtoEntityConverter.convertProductToIdentity(body);
        product = this.productService.createProduct(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a product")
    @PutMapping("/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable Integer id, @RequestBody ProductDtoRequest body) {
        Product product = this.dtoEntityConverter.convertProductToIdentity(body);
        product.setId(id);
        product = this.productService.updateProductById(product, id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @Operation(summary = "Read a product")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        Product product = this.productService.getById(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @Operation(summary = "Remove a product")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable Integer id) {
        this.productService.removeProduct(id);
        return new ResponseEntity<>(new MessageResponse("Product deleted succesfully"), HttpStatus.OK);
    }

    @Operation(summary = "List of products")
    @GetMapping("/list")
    public ResponseEntity<List> getClientList() {
        List<Product> productList = this.productService.getList();
        return new ResponseEntity<List>(productList, HttpStatus.OK);
    }
}