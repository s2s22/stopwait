package com.example.stopwait.restaurant;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class RestaurantJpaRepository implements RestaurRepository{

    private final EntityManager em;

    public RestaurantJpaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public int save(Restaurant restaurant) {
        em.persist(restaurant);
        return restaurant.getId();
    }

    @Override
    public List<Restaurant> findAll() {
        return em.createQuery("select r from Restaurant r", Restaurant.class)
                .getResultList();
    }

    @Override
    public Optional<Restaurant> findById(int restaurantId) {
        return Optional.ofNullable(em.find(Restaurant.class, restaurantId));
    }

    @Override
    public int delete(int id) {
        return em.createQuery("delete from  Restaurant r where r.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
         return em.createQuery("select r from Restaurant r where r.name = :name", Restaurant.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findAny();
    }
}
