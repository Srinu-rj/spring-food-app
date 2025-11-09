package com.foodapp.springfoodapp.repository;

import com.foodapp.springfoodapp.entiry.FoodCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCartRepo extends JpaRepository<FoodCart,Integer> {
}
