package com.example.stopwait.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {


    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public int add(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> findRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant findRestaurant(int restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }
}
