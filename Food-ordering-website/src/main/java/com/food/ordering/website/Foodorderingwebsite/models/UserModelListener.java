package com.food.ordering.website.Foodorderingwebsite.models;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class UserModelListener extends AbstractMongoEventListener<User> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        User user = event.getSource();
        
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(new Date());
        }
        
        user.setUpdatedAt(new Date());
    }
}

