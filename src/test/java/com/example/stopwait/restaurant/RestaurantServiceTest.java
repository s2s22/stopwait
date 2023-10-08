package com.example.stopwait.restaurant;

import com.example.stopwait.category.Category;
import com.example.stopwait.category.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class RestaurantServiceTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired RestaurantService restaurantService;
    @Test
    public void 식당생성() {
        //given
        Category category1 = new Category();
        category1.setName("한식dto");

        Category category2 = new Category();
        category2.setName("백반dto");

        int id1 = categoryRepository.save(category1);
        int id2 = categoryRepository.save(category2);

        RestaurantSaveDto restaurantSaveDto1 = new RestaurantSaveDto();
        restaurantSaveDto1.setName("소영이네");
        restaurantSaveDto1.setContent("먹으러오이소dto");
        restaurantSaveDto1.setRating("4.4");
        restaurantSaveDto1.setCategories(new ArrayList<>(Arrays.asList(1,2)));

        //when
        int id = restaurantService.createRestaurant(restaurantSaveDto1);

        //then
        Restaurant findRestaurant = restaurantService.getRestaurant(id).get();
        Assertions.assertThat(restaurantSaveDto1.getName()).isEqualTo(findRestaurant.getName());
    }

    @Test
    public void 식당수정() {
        //given
        Category category1 = new Category();
        category1.setName("한식");

        Category category2 = new Category();
        category2.setName("백반");

        int id1 = categoryRepository.save(category1);
        int id2 = categoryRepository.save(category2);

        RestaurantSaveDto restaurantSaveDto1 = new RestaurantSaveDto();
        restaurantSaveDto1.setName("소영이네국밥");
        restaurantSaveDto1.setContent("먹으러오이소");
        restaurantSaveDto1.setRating("4.5");
        restaurantSaveDto1.setCategories(new ArrayList<>(Arrays.asList(1,2)));

        //when
        int id = restaurantService.createRestaurant(restaurantSaveDto1);

        RestaurantUpdateDto restaurantUpdateDto = new RestaurantUpdateDto();
        restaurantUpdateDto.setName("소영이네국밥업데이트");
        restaurantService.updateRestaurant(id, restaurantUpdateDto);
    }

}