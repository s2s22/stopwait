package com.example.stopwait.restaurant;

import java.util.*;

public class RestaurantMapRepository {

    private static Map<Integer, Restaurant> db = new HashMap<>();

    //@Override
    public int save(RestaurantSaveDto restaurantSaveDto) {
       // db.put(createRestaurantDto.getId(), restaurant);
        return 1;
    }

    //@Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(db.values());
    }

    //@Override
    public Optional<Restaurant> findById(int restaurantId) {
        return Optional.ofNullable(db.get(restaurantId));
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant updateRest = db.get(restaurant.getId());
        //updateRest.setName(restaurant.getName());
        //updateRest.setContent(restaurant.getContent());
        //updateRest.setCategory(restaurant.getCategory());
        //updateRest.setRating(restaurant.getRating());
        //updateRest.setReview(restaurant.getReview());

        db.put(restaurant.getId(), updateRest);

        return db.get(restaurant.getId());
    }

    //@Override
    public int deleteRestaurant(int restaurantId) {
        db.remove(restaurantId);
        return db.size();
    }

    //@Override
    public Optional<Restaurant> findByName(String name) {
        return Optional.empty();
    }
}
