package com.example.MicroServiceConcept.controller;

import com.example.MicroServiceConcept.dto.ProductRequest;
import com.example.MicroServiceConcept.dto.ProductResponse;
import com.example.MicroServiceConcept.model.Product;
import com.example.MicroServiceConcept.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest product) {
        productService.createProduct(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts() {
        return productService.getAllProducts();
    }
}
