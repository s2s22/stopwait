package com.example.stopwait.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public int createCategory(Category category) {
        return categoryRepository.save(category);
    }

}
