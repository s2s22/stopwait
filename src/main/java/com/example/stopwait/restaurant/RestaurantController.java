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
    public int createRest(@RequestBody Restaurant restaurant) {
        return restaurantService.add(restaurant);
    }

    @GetMapping
    public List<Restaurant> getList() {
        return restaurantService.findRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRest(@PathVariable int restaurantId) {
        return restaurantService.findRestaurant(restaurantId);
    }

    @PatchMapping
    public Restaurant setRest(@RequestBody Restaurant restaurant) {
        return restaurantService.updateRest(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public void deleteRest(@PathVariable int restaurantId) {
        restaurantService.deleteRest(restaurantId);
    }
}
