package com.coder.learn_mongodb;

import com.coder.learn_mongodb.entity.Address;
import com.coder.learn_mongodb.entity.Order;
import com.coder.learn_mongodb.entity.Product;
import com.coder.learn_mongodb.repository.OrderRepository;
import com.coder.learn_mongodb.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RelationshipMongoTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void testOrderCreation() {
        Order lOrder = Order.builder()
                .status("READY")
                .quantity(10)
                .totalPrice(100.0)
                .address(Address.builder()
                        .line1("Line 1 Address")
                        .city("Mumbai")
                        .state("Mumbai")
                        .zipCode("400022")
                        .country("India")
                        .build())
                .build();

        lOrder = orderRepository.insert(lOrder);
        //System.out.println(lOrder);
        System.out.println(lOrder.getStatus());
    }

    @Test
    void testOrderProductCreation() {

        Product lLaptop = Product.builder()
                .name("Gaming Laptop")
                .category("Electronics")
                .price(1299.99)
                .build();
        lLaptop = productRepository.save(lLaptop);

        Product lMouse = Product.builder()
                .name("Wireless Mouse")
                .category("Accessories")
                .price(49.99)
                .build();
        lMouse = productRepository.save(lMouse);

        Order lOrder = Order.builder()
                .status("READY")
                .quantity(10)
                .totalPrice(100.0)
                .address(Address.builder()
                        .line1("Line 1 Address")
                        .city("Mumbai")
                        .state("Mumbai")
                        .zipCode("400022")
                        .country("India")
                        .build())
                .products(List.of(lLaptop, lMouse))
                .build();

        lOrder = orderRepository.save(lOrder);
        System.out.println(lOrder);

    }

    @Test
    void testOrderFetch() {
        //var lOrders = orderRepository.findByAddressCity("Mumbai");
        var lOrders = orderRepository.findByCity("Mumbai");
        lOrders.forEach(System.out::println);
    }

}
