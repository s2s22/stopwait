package com.example.stopwait.restaurant;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RestaurantRepository {

    private static Map<Integer, Restaurant> db = new HashMap<>();

    public int save(Restaurant restaurant) {
        db.put(restaurant.getId(), restaurant);
        return db.get(restaurant.getId()).getId();
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(db.values());
    }

    public Restaurant findById(int restaurantId) {
        return db.get(restaurantId);
    }

    public Restaurant updateRest(Restaurant restaurant) {
        Restaurant updateRest = db.get(restaurant.getId());
        updateRest.setName(restaurant.getName());
        updateRest.setContent(restaurant.getContent());
        updateRest.setCategory(restaurant.getCategory());
        updateRest.setRating(restaurant.getRating());
        updateRest.setReview(restaurant.getReview());

        db.put(restaurant.getId(), updateRest);

        return db.get(restaurant.getId());
    }

    public void deleteRest(int restaurantId) {
        db.remove(restaurantId);
    }
}
