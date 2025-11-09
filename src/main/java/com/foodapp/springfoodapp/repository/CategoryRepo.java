package com.foodapp.springfoodapp.repository;

import com.foodapp.springfoodapp.entiry.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
