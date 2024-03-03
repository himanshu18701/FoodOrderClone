package com.food.ordering.website.Foodorderingwebsite.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.food.ordering.website.Foodorderingwebsite.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
