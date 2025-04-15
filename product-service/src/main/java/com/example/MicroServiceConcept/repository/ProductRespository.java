package com.example.MicroServiceConcept.repository;

import com.example.MicroServiceConcept.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRespository extends MongoRepository<Product, String> {
}
