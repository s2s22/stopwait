package com.example.stopwait.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int createRestaurant(Restaurant restaurant) {
        vaildateDulplicateRest(restaurant);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRest() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRest(int restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    public Restaurant updateRest(int restaurantId, UpdateRestDto updateRestDto) {

        Restaurant updateRest = restaurantRepository.findById(restaurantId).get();
        updateRest.setName(updateRestDto.getName());
        updateRest.setRating(updateRestDto.getRating());
        updateRest.setContent(updateRestDto.getContent());
        //updateRest.setReview(updateRestDto.getReview());

        vaildateDulplicateRest(updateRest);

        restaurantRepository.save(updateRest);

        return updateRest;
    }

    public void deleteRestaurant(int restaurantId) {
        restaurantRepository.deleteRestaurant(restaurantId);
    }

    public void vaildateDulplicateRest(Restaurant restaurant) {
        LOG.error("service error exist : {}" , restaurant.getName());
        restaurantRepository.findByName(restaurant.getName())
                        .ifPresent(rest -> {
                            throw new IllegalStateException("이미 존재하는 식당명입니다.");
                        });
    }
}
