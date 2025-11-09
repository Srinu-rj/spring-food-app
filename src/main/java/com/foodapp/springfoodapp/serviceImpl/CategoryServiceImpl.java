package com.foodapp.springfoodapp.serviceImpl;

import com.foodapp.springfoodapp.entiry.Category;
import com.foodapp.springfoodapp.repository.CategoryRepo;
import com.foodapp.springfoodapp.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> saveCategory(List<Category> categories) {

        return categoryRepo.saveAll(categories);
    }

    @Override
    public List<Category> getAllcategorys() {
        return categoryRepo.findAll();
    }

    @Transactional
    @Override
    public String deleteCategoryById(Integer categoryId) {
        categoryRepo.findById(categoryId);
        return "categoryID Deleted";
    }

    
    @Override
    public Category updatecategory(Category updateCategory, Integer id) {
        Category exitCategory = categoryRepo.findById(updateCategory.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("you need to create category"));
        if (exitCategory.getCategoryName() != null) {
            exitCategory.setCategoryName(updateCategory.getCategoryName());
        }

        categoryRepo.save(exitCategory);
        return exitCategory;
    }
}
