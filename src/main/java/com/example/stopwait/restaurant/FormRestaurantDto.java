package com.example.stopwait.restaurant;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter @Getter
public class FormRestaurantDto {

    private String name;

    private String content;

    private String rating;

    private List<Map<Integer, String>> categories;
}
