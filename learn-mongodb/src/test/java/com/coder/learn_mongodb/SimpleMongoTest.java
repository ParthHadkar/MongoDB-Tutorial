package com.coder.learn_mongodb;

import com.coder.learn_mongodb.entity.Order;
import com.coder.learn_mongodb.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class SimpleMongoTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void testCreateOrder() {
        Order lOrder = Order.builder()
                .status("READY")
                .quantity(10)
                .totalPrice(100.0)
                .build();

        lOrder = orderRepository.save(lOrder);
        System.out.println(lOrder);

    }

    @Test
    void testCreateOrderDynamic() {
        for (int i = 1; i < 10; i++) {
            Order lOrder = Order.builder()
                    .status("READY")
                    .quantity(10 * i)
                    .totalPrice(100.0 * i)
                    .build();

            lOrder = orderRepository.save(lOrder);
            System.out.println(lOrder);
        }

    }

    @Test
    void testGetOrder() {
        //List<Order> lOrderList = orderRepository.findByStatusAndQuantityGreaterThan("READY", 2);
        //List<Order> lOrderList = orderRepository.findByStatusAndQuantityGreaterThanOrderByCreatedAtDesc("READY", 2);
        //List<Order> lOrderList = orderRepository.findOrdersByStatusAboveAndPrice("READY", 10);
        List<Order> lOrderList = orderRepository.findOrdersByStatusAboveAndQuantity("READY", 10);

        lOrderList.forEach(System.out::println);
    }

    @Test
    void deleteOrder() {
        List<Order> lOrderList = orderRepository.findOrdersByStatusAboveAndPrice("READY", 10);
        lOrderList.forEach(System.out::println);

        orderRepository.deleteAll(lOrderList);

        lOrderList = orderRepository.findOrdersByStatusAboveAndPrice("READY", 10);
        lOrderList.forEach(System.out::println); // should be empty

    }

    @Test
    void testPagination() {

        Pageable lPageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "totalPrice")); // 1 next page
        List<Order> lOrderList = orderRepository.findAll(lPageable).toList();

        lOrderList.forEach(System.out::println);
    }

}
