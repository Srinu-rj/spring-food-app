package com.foodapp.springfoodapp.controller;

import com.foodapp.springfoodapp.entiry.Restaurant;
import com.foodapp.springfoodapp.exception.UserException;
import com.foodapp.springfoodapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1.prefix}/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/add")
    public ResponseEntity<Restaurant> restaurant(@RequestBody Restaurant restaurant) throws UserException {
        Restaurant saveRestaurant = restaurantService.saveRestaurant(restaurant);
        return new ResponseEntity<>(saveRestaurant, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addMore")
    public ResponseEntity<List<Restaurant>> saveRestaurantsNames(@RequestBody List<Restaurant> restaurants) {
        List<Restaurant> restaurantList = restaurantService.saveRestaurants(restaurants);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantList);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Restaurant>> fetchAllRestaurant() {
        List<Restaurant> getListOfRestaurant = restaurantService.getAllRestants();
        return ResponseEntity.status(HttpStatus.CREATED).body(getListOfRestaurant);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        Restaurant updateRestaurant = restaurantService.updateRestaurant(restaurant, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateRestaurant);
    }

    @DeleteMapping("/deleteRestaurentId/{restaurantId}")
    public ResponseEntity<String> deleteRestaurantById(@PathVariable int restaurantId) {
        String deleteRestaurant = restaurantService.deleterestaurant(restaurantId);
        return ResponseEntity.ok(deleteRestaurant);
    }

    @GetMapping("/manager/{managerName}")
    public ResponseEntity<Restaurant> findRestaurantManagerName(@PathVariable String managerName) {
        Restaurant findRestaurantManagerName = restaurantService.findManagerName(managerName);
        return ResponseEntity.status(HttpStatus.CREATED).body(findRestaurantManagerName);
    }

}

