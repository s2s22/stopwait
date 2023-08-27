package com.example.stopwait.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        return restaurantService.createRest(restaurant);
    }

    @GetMapping
    public List<Restaurant> getAllRest() {
        return restaurantService.getAllRest();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRest(@PathVariable int restaurantId) {
        Restaurant rest = restaurantService.getRest(restaurantId);

        HttpHeaders headers = new HttpHeaders();

        if (rest == null) {
            return ResponseEntity.badRequest()
                    .body(rest);
        }

        return ResponseEntity.ok()
                .body(rest);
    }


    @PatchMapping
    public Restaurant updateRest(@RequestBody Restaurant restaurant) {
        return restaurantService.updateRest(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public void deleteRest(@PathVariable int restaurantId) {
        restaurantService.deleteRest(restaurantId);
    }
}
