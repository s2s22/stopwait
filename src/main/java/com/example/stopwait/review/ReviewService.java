package com.example.stopwait.review;

import com.example.stopwait.restaurant.Restaurant;
import com.example.stopwait.restaurant.RestaurantJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRespository reviewRespository;
    private final RestaurantJpaRepository restaurantJpaRepository;

    public int createReview(int restaurantid, Review review) {
        Optional<Restaurant> findRestaurant = restaurantJpaRepository.findById(restaurantid);
        review.setRestaurant(findRestaurant.get());
        return reviewRespository.save(review);
    }

    public int deleteReview(int reviewId) {
        return reviewRespository.deleteReview(reviewId);
    }
}
