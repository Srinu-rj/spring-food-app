package com.foodapp.springfoodapp.service;


import com.foodapp.springfoodapp.entiry.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
	
	Category addCategory(Category categoryDto);
	
	List<Category> saveCategory(List<Category>categories );
	
	List<Category> getAllcategorys();

    String deleteCategoryById(Integer categoryId);

    Category updatecategory(Category updateCategory, Integer id);
}
