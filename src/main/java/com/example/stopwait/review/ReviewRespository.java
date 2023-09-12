package com.example.stopwait.review;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ReviewRespository {

    private final EntityManager em;

    public ReviewRespository(EntityManager em) {
        this.em = em;
    }

    public int save(Review review) {
        em.persist(review);
        return review.getId();
    }

    /*public List<Review> findByRestaurantId(int restaurantId) {
        return em.createQuery("select r from Review r where r.restaurant.id = :restaurantId", Review.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }*/

    public int deleteReview(int reviewId) {
        return em.createQuery("delete from Review r where r.id = :id")
                .setParameter("id", reviewId)
                .executeUpdate();

    }
}
