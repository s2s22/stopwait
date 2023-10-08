package com.example.stopwait.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter
public class RestaurantSaveDto {

    @NotBlank
    private String name;

    private String content;

    private String rating;

    private List<Integer> categories;

    public RestaurantSaveDto() {
    }
}
