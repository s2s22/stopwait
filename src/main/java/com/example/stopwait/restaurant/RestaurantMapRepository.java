package com.example.stopwait.restaurant;

import org.springframework.stereotype.Repository;

import java.util.*;

public class RestaurantMapRepository implements RestaurRepository{

    private static Map<Integer, Restaurant> db = new HashMap<>();

    @Override
    public int save(Restaurant restaurant) {
        db.put(restaurant.getId(), restaurant);
        return db.get(restaurant.getId()).getId();
    }

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<Restaurant> findById(int restaurantId) {
        return Optional.ofNullable(db.get(restaurantId));
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant updateRest = db.get(restaurant.getId());
        updateRest.setName(restaurant.getName());
        updateRest.setContent(restaurant.getContent());
        //updateRest.setCategory(restaurant.getCategory());
        updateRest.setRating(restaurant.getRating());
        updateRest.setReview(restaurant.getReview());

        db.put(restaurant.getId(), updateRest);

        return db.get(restaurant.getId());
    }

    @Override
    public int delete(int restaurantId) {
        db.remove(restaurantId);
        return db.size();
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        return Optional.empty();
    }
}
