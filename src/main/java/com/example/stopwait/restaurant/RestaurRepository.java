package com.example.stopwait.restaurant;

import java.util.List;

public interface RestaurRepository {

    int save(Restaurant restaurant);
    List<Restaurant> findAll();
    Restaurant findById(int restaurantId);

    Restaurant update(Restaurant restaurant);

    void delete(int restaurantId);

}
