package com.example.stopwait.review;

import com.example.stopwait.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter @Setter
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    @NotBlank(message = "리뷰 제목은 필수값입니다.")
    String title;

    @NotBlank(message = "내용을 입력해주세요.")
    String content;
}
