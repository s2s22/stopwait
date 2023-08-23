package com.example.stopwait.restaurant;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RestaurantRepository {

    private static Map<Integer, Restaurant> db = new HashMap<>();
    private static int seq = 0;

    public int save(Restaurant restaurant) {
        db.put(++seq, restaurant);
        return db.get(seq).getId();
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(db.values());
    }

    public Restaurant findById(int restaurantId) {
        return db.get(restaurantId);
    }
}
