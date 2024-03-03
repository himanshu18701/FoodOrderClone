package com.food.ordering.website.Foodorderingwebsite.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.food.ordering.website.Foodorderingwebsite.models.Food;

import java.util.List;
import java.util.regex.Pattern;

public interface FoodRepository extends MongoRepository<Food, String> {
    List<Food> findByNameRegex(Pattern regex);
    List<Food> findByTags(String tag);
}
