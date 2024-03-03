package com.food.ordering.website.Foodorderingwebsite;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
        		"cloud_name", "dw4uvqzrv",
        		  "api_key", "443155452874424",
        		  "api_secret", "X8-k2aHdZo7N8J19PzwsIuvLRNs"));
    }

}