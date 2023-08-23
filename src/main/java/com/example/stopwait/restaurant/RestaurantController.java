package com.example.stopwait.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurants")
@ResponseBody
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public int create(@RequestBody Restaurant restaurant) {
        return restaurantService.add(restaurant);
    }

    @GetMapping
    public List<Restaurant> listRestaurants() {
        return restaurantService.findRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant restaurant(@PathVariable int restaurantId) {
        return restaurantService.findRestaurant(restaurantId);
    }
}
