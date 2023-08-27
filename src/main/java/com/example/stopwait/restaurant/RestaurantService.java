package com.example.stopwait.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public int createRest(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRest() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRest(int restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    public Restaurant updateRest(Restaurant restaurant) {
        return restaurantRepository.update(restaurant);
    }

    public void deleteRest(int restaurantId) {
        restaurantRepository.delete(restaurantId);
    }
}
