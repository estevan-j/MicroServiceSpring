package com.example.MicroServiceConcept.service;

import com.example.MicroServiceConcept.dto.ProductRequest;
import com.example.MicroServiceConcept.dto.ProductResponse;
import com.example.MicroServiceConcept.model.Product;
import com.example.MicroServiceConcept.repository.ProductRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRespository productRespository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRespository.save(product);
        log.info("Product created: " + product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products= productRespository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
