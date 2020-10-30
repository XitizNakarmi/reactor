package com.example.reactortest.Repository;

import com.example.reactortest.Model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> { }
