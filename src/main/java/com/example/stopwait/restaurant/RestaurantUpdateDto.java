package com.example.stopwait.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class RestaurantUpdateDto {

    @NotNull
    private int id;

    @NotBlank
    private String name;

    private String content;

    private String rating;

    private List<Integer> categories;
}
