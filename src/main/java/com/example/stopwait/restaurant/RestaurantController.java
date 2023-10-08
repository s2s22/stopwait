package com.example.stopwait.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PostMapping
    public ResponseEntity createRestaurant(@RequestBody @Validated RestaurantSaveDto restaurantSaveDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("식당 에러 = {}",  bindingResult);
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors());
        }

        int newRest = restaurantService.createRestaurant(restaurantSaveDto);

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
    public ResponseEntity<List<Restaurant>> getAllRestarant() {
        List<Restaurant> allRest = restaurantService.getAllRestaurant();
        log.info("restaurants :{}", allRest.get(0).getName());
        log.info("restaurants :{}", allRest.get(0).getReviews().get(0));
        return ResponseEntity.ok()
                .body(allRest);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestarant(@PathVariable int restaurantId) {
        Optional<Restaurant> rest = restaurantService.getRestaurant(restaurantId);

        HttpHeaders headers = new HttpHeaders();

        if (!rest.isPresent()) {
            log.warn("Not Exist");
            return ResponseEntity.badRequest()
                    .body(Restaurant.builder().build());
        }

        return ResponseEntity.ok()
                .body(rest.get());

    }


    @PatchMapping("{restaurantId}")
    public ResponseEntity<?> updateRest(@PathVariable int restaurantId, @RequestBody @Validated RestaurantUpdateDto restaurantUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("식당 에러 = {}",  bindingResult);
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors());
        }
        Restaurant updateRest = restaurantService.updateRestaurant(restaurantId, restaurantUpdateDto);

        return ResponseEntity.ok()
                .body(updateRest);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity deleteRestaurant(@PathVariable int restaurantId) {

        restaurantService.deleteRestaurant(restaurantId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalStateException.class) //입력값이 잘못들어왔을때 타입미스매치
    public ResponseEntity<String> handleException(IllegalStateException e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }
}
