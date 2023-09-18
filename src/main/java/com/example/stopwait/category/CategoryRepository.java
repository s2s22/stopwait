package com.example.stopwait.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class CategoryRepository {

    private final EntityManager em;

    public int save(Category category) {
        em.persist(category);
        return category.getId();
    }
}
