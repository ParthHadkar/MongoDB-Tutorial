package com.coder.learn_mongodb.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@Builder
public class Product {

    @Id
    private String id;

    private String name;

    @Indexed
    private String category;

    @Indexed
    private double price;

}
