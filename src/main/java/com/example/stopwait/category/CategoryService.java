package com.example.stopwait.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public int createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findOne(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Transactional
    public void modifyCategory(int categoryId, UpdateCategoryDto updateCategoryDto) {
        Category modifyCategory = categoryRepository.findById(categoryId).get();
        modifyCategory.setName(updateCategoryDto.getName());
    }

    public int deleteCategory(int categoryId) {
        return categoryRepository.deleteCategory(categoryId);
    }
}
