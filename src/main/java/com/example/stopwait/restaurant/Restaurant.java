package com.example.stopwait.restaurant;

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


@Entity
@Getter @Setter
@Transactional
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "식당명은 필수입니다.")
    private String name;

    private String content;

    //@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    //private List<RestaurantCategory> restaurantCategories = new ArrayList<>();

    private String rating;

    @OneToMany( mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

}
