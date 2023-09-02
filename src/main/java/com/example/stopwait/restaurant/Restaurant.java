package com.example.stopwait.restaurant;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Optional;


@Entity
@Data
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank
    String name;
    String content;

    //Category category;

    String rating;

    String review;



}
