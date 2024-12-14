package com.foodapp.springfoodapp.controller;

import com.foodapp.springfoodapp.entiry.Category;
import com.foodapp.springfoodapp.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) {
        Category saveCategory = categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCategory);
    }

    @PostMapping("/addMore")
    public ResponseEntity<List<Category>> addListCategory(@RequestBody @Valid List<Category> categories) {
        List<Category> categoryList = categoryService.saveCategory(categories);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryList);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Category>> findAllCategory() {
        List<Category> category = categoryService.getAllcategorys();
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
        String deleteCategoryId = categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteCategoryId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateAddress(@RequestBody Category updateCategory, Integer id) {
        Category categoryUpdate = categoryService.updatecategory(updateCategory, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryUpdate);
    }


}
