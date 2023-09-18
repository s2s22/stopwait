package com.example.stopwait.review;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity createReview(@PathVariable int restaurantId, @RequestBody Review review) {
        int createReview = reviewService.createReview(restaurantId, review);

        log.info("cuurent = {}" ,ServletUriComponentsBuilder
                .fromCurrentRequest() );

        log.info("path = {}" , ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(""));

        log.info("create = {}" , ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(createReview));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(createReview)
                .toUri();

        log.info("최종uri = {}", uri);
        return ResponseEntity.created(uri)
                .body(createReview);

    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity deleteReview(@PathVariable int reviewId) {
        int deleteCnt = reviewService.deleteReview(reviewId);

        if (deleteCnt <= 0) {
            return ResponseEntity
                    .badRequest()
                    .body("삭제된 리뷰가 없습니다");
        }
        return ResponseEntity
                .noContent()
                .build();
    }
}
