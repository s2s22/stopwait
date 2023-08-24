package com.example.stopwait.restaurant;

public class Restaurant {

    int id;
    String name;
    String content;

    Category category;

    String rating;

    String review;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Restaurant setName(String name) {
        this.name = name;
        return this;
    }

    public Restaurant setContent(String content) {
        this.content = content;
        return this;
    }

    public Restaurant setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Restaurant setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public Restaurant setReview(String review) {
        this.review = review;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public String getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}
