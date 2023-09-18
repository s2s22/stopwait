package com.example.stopwait.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurRepository {

    int save(Restaurant restaurant);
    List<Restaurant> findAll();
    Optional<Restaurant> findById(int restaurantId);

    int deleteRestaurant(int restaurantId);

    Optional<Restaurant> findByName(String name);

}
