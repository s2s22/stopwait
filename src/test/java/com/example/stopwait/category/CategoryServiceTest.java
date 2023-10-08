package com.example.stopwait.category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
class CategoryServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired CategoryService categoryService;

    @Test
    public void 카테고리저장테스트() {

        //given
        Category category1 = new Category();
        category1.setName("한식");

        Category category2 = new Category();
        category2.setName("중식");

        Category category3 = new Category();
        category3.setName("일식");

        //when
        categoryService.createCategory(category1);
        categoryService.createCategory(category2);
        categoryService.createCategory(category3);

        //then
        categoryService.findCategories()
                .forEach(category -> System.out.println(category.getName()));

    }
    
    @Test
    @Transactional
    public void 수정테스트() {
        //given
        Category category = new Category();
        category.setName("한식");
        int id = categoryService.createCategory(category);

        UpdateCategoryDto updateCategoryDto = new UpdateCategoryDto();
        updateCategoryDto.setName("한식업데이트");
        
        //when
        categoryService.modifyCategory(id, updateCategoryDto);
        
        //then
        Category findCategory = categoryService.findOne(id).get();
        Assertions.assertThat(findCategory.getName()).isEqualTo("한식업데이트");
        
    }

}