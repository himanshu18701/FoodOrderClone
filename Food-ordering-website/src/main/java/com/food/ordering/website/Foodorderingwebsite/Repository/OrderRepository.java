package com.food.ordering.website.Foodorderingwebsite.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.food.ordering.website.Foodorderingwebsite.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
	 Optional<Order> findTopByUserIdAndStatusOrderByCreatedAtDesc(String userId, String status);
	 List<Order> findByStatusAndUserId(String status, String userId);
}