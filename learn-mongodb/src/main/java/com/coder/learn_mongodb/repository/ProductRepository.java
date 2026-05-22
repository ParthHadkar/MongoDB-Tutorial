package com.coder.learn_mongodb.repository;

import com.coder.learn_mongodb.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
