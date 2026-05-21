package com.coder.learn_mongodb.repository;

import com.coder.learn_mongodb.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByStatusAndQuantityGreaterThan(String pStatus, Integer pQuantity);

    List<Order> findByStatusAndQuantityGreaterThanOrderByCreatedAtDesc(String pStatus, Integer pQuantity);

    @Query("{ 'status': ?0, 'totalPrice': { $gte: ?1 } }")
    List<Order> findOrdersByStatusAboveAndPrice(String status, double minPrice);

    @Query("{ 'status': :#{#status}, 'quantity': { $gt: :#{#minQty} } }")
    List<Order> findOrdersByStatusAboveAndQuantity(@Param("status") String status,
                                 @Param("minQty") int minQuantity);

}
