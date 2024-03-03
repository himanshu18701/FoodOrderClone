package com.food.ordering.website.Foodorderingwebsite.Controllers;

import com.food.ordering.website.Foodorderingwebsite.Services.OrderService;
import com.food.ordering.website.Foodorderingwebsite.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order, Principal principal) {
        Order newOrder = orderService.createOrder(order, principal.getName());
        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/pay")
    public ResponseEntity<?> payForOrder(@RequestBody Map<String, String> requestBody, Principal principal) {
        String orderId = requestBody.get("orderId");
        String paymentId = requestBody.get("paymentId");
        boolean success = orderService.payForOrder(orderId, paymentId, principal.getName());
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Payment failed or Order not found");
        }
    }

    @GetMapping("/track/{orderId}")
    public ResponseEntity<?> trackOrder(@PathVariable String orderId, Principal principal) {
        return orderService.trackOrderById(orderId, principal.getName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/newOrderForCurrentUser")
    public ResponseEntity<?> getNewOrderForCurrentUser(Principal principal) {
        return orderService.getNewOrderForCurrentUser(principal.getName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status, Principal principal) {
        List<Order> orders = orderService.getOrdersByStatus(status, principal.getName());
        return ResponseEntity.ok(orders);
    }
}
