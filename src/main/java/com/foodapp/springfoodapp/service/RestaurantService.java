package com.foodapp.springfoodapp.service;

import com.foodapp.springfoodapp.entiry.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
	List<Restaurant> saveRestaurants(List<Restaurant> restaurants);
	List<Restaurant> getAllRestants();
	String deleterestaurant(int restaurantId);
	Restaurant findManagerName(String managerName);


	Restaurant updateRestaurant(Restaurant restaurant, int id);

	Restaurant saveRestaurant(Restaurant restaurant);
}
