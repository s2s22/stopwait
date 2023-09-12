package com.example.stopwait.restaurant;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ㄱ")
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }


    @PostMapping
    public ResponseEntity createRestaurant(@RequestBody Restaurant restaurant) {
        int newRest = restaurantService.createRestaurant(restaurant);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(newRest)
                .toUri();

        return ResponseEntity.created(location)
                .body(newRest)
                ;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRest() {
        List<Restaurant> allRest = restaurantService.getAllRest();
        log.info("restaurants :{}", allRest.get(0).getName());
        log.info("restaurants :{}", allRest.get(0).getReviews().get(0));
        return ResponseEntity.ok()
                .body(allRest);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRest(@PathVariable int restaurantId) {
        Optional<Restaurant> rest = restaurantService.getRest(restaurantId);

        HttpHeaders headers = new HttpHeaders();

        if (!rest.isPresent()) {
            log.warn("Not Exist");
            return ResponseEntity.badRequest()
                    .body(new Restaurant());
        }

        return ResponseEntity.ok()
                .body(rest.get());

    }


    @PatchMapping("{restaurantId}")
    public ResponseEntity<Restaurant> updateRest(int restaurantId, @RequestBody UpdateRestDto updateRestDto) {
        Restaurant updateRest = restaurantService.updateRest(restaurantId, updateRestDto);

        return ResponseEntity.ok()
                .body(updateRest);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity deleteRestaurant(@PathVariable int restaurantId) {

        restaurantService.deleteRestaurant(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleException(IllegalStateException e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }
}
