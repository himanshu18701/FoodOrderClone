package com.food.ordering.website.Foodorderingwebsite.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.food.ordering.website.Foodorderingwebsite.Repository.FoodRepository;
import com.food.ordering.website.Foodorderingwebsite.models.Food;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("")
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @PostMapping("")
    public Food createFood(@RequestBody Food food) {
        return foodRepository.save(food);
    }

    @PutMapping("")
    public Food updateFood(@RequestBody Food food) {
        return foodRepository.save(food);
    }

    @DeleteMapping("/{foodId}")
    public void deleteFood(@PathVariable String foodId) {
        foodRepository.deleteById(foodId);
    }

    @GetMapping("/tags")
    public List<String> getTags() {
        return foodRepository.findAll().stream()
                .flatMap(food -> food.getTags().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @GetMapping("/search/{searchTerm}")
    public List<Food> searchFoods(@PathVariable String searchTerm) {
        Pattern searchPattern = Pattern.compile(searchTerm, Pattern.CASE_INSENSITIVE);
        return foodRepository.findByNameRegex(searchPattern);
    }

    @GetMapping("/tag/{tag}")
    public List<Food> getFoodsByTag(@PathVariable String tag) {
        return foodRepository.findByTags(tag);
    }

    @GetMapping("/{foodId}")
    public Food getFoodById(@PathVariable String foodId) {
        Optional<Food> food = foodRepository.findById(foodId);
        return food.orElse(null);
    }
}
