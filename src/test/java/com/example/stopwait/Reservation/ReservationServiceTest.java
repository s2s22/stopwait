package com.example.stopwait.Reservation;

import com.example.stopwait.category.Category;
import com.example.stopwait.category.CategoryRepository;
import com.example.stopwait.restaurant.RestaurantSaveDto;
import com.example.stopwait.restaurant.RestaurantJpaRepository;
import com.example.stopwait.restaurant.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    RestaurantJpaRepository restaurantJpaRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired ReservationService reservationService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void 식당예약() {
        //given
        Category category1 = new Category();
        category1.setName("한식");

        Category category2 = new Category();
        category2.setName("백반");

        int id1 = categoryRepository.save(category1);
        int id2 = categoryRepository.save(category2);


        RestaurantSaveDto restaurantSaveDto1 = new RestaurantSaveDto();
        restaurantSaveDto1.setName("소영이네국밥");
        restaurantSaveDto1.setContent("먹으러오이소so");
        restaurantSaveDto1.setRating("4.4");
        restaurantSaveDto1.setCategories(new ArrayList<>(Arrays.asList(1,2)));

        //when
        int id = restaurantService.createRestaurant(restaurantSaveDto1);

        ReservationSaveDto reservationSaveDto = new ReservationSaveDto();
        reservationSaveDto.setReservationDt(LocalDate.of(2023, 11, 2));
        reservationSaveDto.setReservationTime(LocalTime.of(10, 00));
        reservationSaveDto.setReservationStatus(ReservationStatus.RESERVATION);
        reservationSaveDto.setDeposit(100000);
        reservationService.reserve(id,reservationSaveDto);

    }

    @Test
    public void 식당예약취소() {

        reservationService.cancel(1);
    }

    @Test
    public void 식당예약수정() {


        ReservationModifyDto reservationModifyDto = new ReservationModifyDto();
        reservationModifyDto.setReservationDt(LocalDate.of(2023, 11, 3));

        reservationService.modify(1, reservationModifyDto);
    }
}