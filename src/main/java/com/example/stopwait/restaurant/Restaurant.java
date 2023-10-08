package com.example.stopwait.restaurant;

import com.example.stopwait.Reservation.Reservation;
import com.example.stopwait.category.RestaurantCategory;
import com.example.stopwait.review.Review;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Entity
@Getter
@NoArgsConstructor
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String content;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantCategory> restaurantCategories = new ArrayList<>();

    public void addRestaurantCategory(RestaurantCategory restaurantCategory) {
        restaurantCategory.setRestaurant(this);
        restaurantCategories.add(restaurantCategory);
    }

    private String rating;

    @OneToMany( mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Reservation> reservation = new ArrayList<>();

    @Transient
    private List<Integer> categories;

    @Builder
    public Restaurant(String name, String content, String rating, List categories) {
        this.name = name;
        this.content = content;
        this.rating = rating;
        this.categories = categories;
    }

    public void change(String name, String content, String rating) {
        this.name = StringUtils.hasText(name) ? name : this.name;
        this.content = StringUtils.hasText(content) ? content : this.content;
        this.rating = StringUtils.hasText(rating) ? rating : this.rating;
    }
}
