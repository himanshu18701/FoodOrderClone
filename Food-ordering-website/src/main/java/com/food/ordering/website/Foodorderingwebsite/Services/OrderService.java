package com.food.ordering.website.Foodorderingwebsite.Services;

import com.food.ordering.website.Foodorderingwebsite.Repository.OrderRepository;
import com.food.ordering.website.Foodorderingwebsite.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order, String userId) {
        order.setUserId(userId);
        order.setStatus("NEW");
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        return orderRepository.save(order);
    }

    public Optional<Order> getNewOrderForCurrentUser(String userId) {
        return orderRepository.findTopByUserIdAndStatusOrderByCreatedAtDesc(userId, "NEW");
    }

    public boolean payForOrder(String orderId, String paymentId, String userId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent() && orderOptional.get().getUserId().equals(userId)) {
            Order order = orderOptional.get();
            order.setPaymentId(paymentId);
            order.setStatus("PAYED");
            order.setUpdatedAt(new Date());
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public Optional<Order> trackOrderById(String orderId, String userId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.filter(o -> o.getUserId().equals(userId));
    }

    public List<Order> getOrdersByStatus(String status, String userId) {
        return orderRepository.findByStatusAndUserId(status, userId);
    }
}

