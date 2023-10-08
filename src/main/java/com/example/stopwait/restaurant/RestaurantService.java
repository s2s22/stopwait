package com.example.stopwait.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurRepository restaurantRepository;

    private  static final Logger LOG = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    public RestaurantService(RestaurRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public int createRestaurant(RestaurantSaveDto restaurantSaveDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantSaveDto.getName())
                .content(restaurantSaveDto.getContent())
                .rating(restaurantSaveDto.getRating())
                .categories(restaurantSaveDto.getCategories())
                .build();
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurant(int restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    @Transactional
    public Restaurant updateRestaurant(int restaurantId, RestaurantUpdateDto restaurantUpdateDto) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        restaurant.change(restaurantUpdateDto.getName(), restaurantUpdateDto.getContent(), restaurantUpdateDto.getRating());

        return restaurant;
    }

    public void deleteRestaurant(int restaurantId) {
        restaurantRepository.deleteRestaurant(restaurantId);
    }

}
