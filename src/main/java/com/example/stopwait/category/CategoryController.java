package com.example.stopwait.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity createCategory(@RequestBody Category category) {
        int newCategory = categoryService.createCategory(category);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(newCategory)
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> categoryList() {
        List<Category> categories = categoryService.findCategories();

        return ResponseEntity.ok()
                .body(categories);

    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> categoryOne(@PathVariable int categoryId) {
        Optional<Category> findCategory = categoryService.findOne(categoryId);

        if (findCategory.isPresent()) {
            return ResponseEntity.ok()
                    .body(findCategory.get());
        } else {
            return ResponseEntity.badRequest()
                    .body(new Category());
        }

    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity modifyCategory(@PathVariable int categoryId, @RequestBody UpdateCategoryDto updateCategoryDto) {
        categoryService.modifyCategory(categoryId, updateCategoryDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable int categoryId) {
        return ResponseEntity.noContent().build();
    }
}
