package com.coder.learn_mongodb;

import com.coder.learn_mongodb.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MongoTemplateTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void mongoTemplateTest() {

        //List<Order> lOrders = mongoTemplate.findAll(Order.class);

        /*Query lQuery = new Query(
                Criteria.where("status").in("READY", "pending")
                        .and("totalPrice").gt(120)
        );*/

        Query lQuery = new Query(
                new Criteria().orOperator(
                        Criteria.where("totalPrice").lte(120),
                        Criteria.where("status").is("pending")
                )
        );

        lQuery.fields().include("status", "id");

        lQuery.limit(2); // pagination



        List<Order> lOrders = mongoTemplate.find(lQuery, Order.class);
        lOrders.forEach(System.out::println);

    }

    @Test
    void mongoTemplateUpdateTest() {
        Query lQuery = new Query(
                Criteria.where("status").is("READY")
        );

        Update lUpdate = new Update()
                .set("status", "SHIPPED")
                .set("updatedAt", new Date());

        mongoTemplate.updateMulti(lQuery, lUpdate, Order.class);
    }

}
