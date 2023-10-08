package com.example.stopwait.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryRepository {

    private final EntityManager em;

    public int save(Category category) {
        em.persist(category);
        return category.getId();
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    public Optional<Category> findById(int categoryId) {
        return Optional.ofNullable(em.find(Category.class, categoryId));
    }

    public int deleteCategory(int categoryId) {
        return em.createQuery("delete from Category c where c.id = :id")
                .setParameter("id", categoryId)
                .executeUpdate();
    }
}
