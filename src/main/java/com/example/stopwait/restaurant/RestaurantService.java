package com.example.stopwait.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public int createRest(Restaurant restaurant) {
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
        updateRest.setReview(updateRestDto.getReview());

        vaildateDulplicateRest(updateRest);

        restaurantRepository.save(updateRest);

        return updateRest;
    }

    public void deleteRest(int restaurantId) {
        restaurantRepository.delete(restaurantId);
    }

    public void vaildateDulplicateRest(Restaurant restaurant) {
        restaurantRepository.findByName(restaurant.getName())
                        .ifPresent(rest -> {
                            throw new IllegalStateException("이미 존재하는 식당명입니다.");
                        });
    }
}
