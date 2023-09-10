package com.example.stopwait.restaurant;

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
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }


    @PostMapping
    public ResponseEntity createRest(@RequestBody Restaurant restaurant) {
        int newRest = restaurantService.createRest(restaurant);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{restuarantId}")
                .buildAndExpand(newRest)
                .toUri();

        return ResponseEntity.created(location)
                .body(newRest)
                ;
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRest() {
        List<Restaurant> allRest = restaurantService.getAllRest();

        return ResponseEntity.ok()
                .body(allRest);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRest(@PathVariable int restaurantId) {
        Optional<Restaurant> rest = restaurantService.getRest(restaurantId);

        HttpHeaders headers = new HttpHeaders();

        if (!rest.isPresent()) {
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
    public ResponseEntity deleteRest(@PathVariable int restaurantId) {

        restaurantService.deleteRest(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleException(IllegalStateException e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }
}
