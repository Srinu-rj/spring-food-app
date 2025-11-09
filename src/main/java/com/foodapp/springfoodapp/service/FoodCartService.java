package com.foodapp.springfoodapp.service;

import com.foodapp.springfoodapp.entiry.FoodCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodCartService {

	List<FoodCart> savefoods(List<FoodCart> foodCarts);

	List<FoodCart> getfood();

	FoodCart savecart(FoodCart foodCart);

	String deleteId(Integer cartId);

	FoodCart updateFoodCartId(FoodCart foodCart, Integer id);

    FoodCart getfoodByid(int foodId) ;
}
