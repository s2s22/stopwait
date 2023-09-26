package com.example.stopwait.restaurant;

import com.example.stopwait.Reservation.Reservation;
import com.example.stopwait.category.RestaurantCategory;
import com.example.stopwait.review.Review;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Entity
@Getter @Setter
@Transactional
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "식당명은 필수입니다.")
    @Column(nullable = false)
    private String name;

    @Lob
    private String content;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantCategory> restaurantCategories = new ArrayList<>();

    @Transient
    private List<Integer> categories;

    public void addRestaurantCategory(RestaurantCategory restaurantCategory) {
        restaurantCategory.setRestaurant(this);
        restaurantCategories.add(restaurantCategory);
    }

    private String rating;

    @OneToMany( mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Reservation> reservation = new ArrayList<>();

}
