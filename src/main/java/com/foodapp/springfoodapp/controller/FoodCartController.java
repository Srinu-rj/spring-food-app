package com.foodapp.springfoodapp.controller;

import com.foodapp.springfoodapp.entiry.FoodCart;
import com.foodapp.springfoodapp.service.FoodCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1.prefix}/food")
public class FoodCartController {

    private final FoodCartService cartService;

    @PostMapping("/save")
    public ResponseEntity<FoodCart> saveCart(@RequestBody @Valid FoodCart foodCart) {
        FoodCart saveCart = cartService.savecart(foodCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCart);
    }

    @PostMapping("/addMore")
    public ResponseEntity<List<FoodCart>> addFoods(@RequestBody @Valid List<FoodCart> foodCarts) {
        List<FoodCart> foodCartList = cartService.savefoods(foodCarts);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodCartList);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodCart> getFoodById(@PathVariable int foodId)  {
        FoodCart getId = cartService.getfoodByid(foodId);
        return ResponseEntity.status(HttpStatus.CREATED).body(getId);
    }

    @GetMapping("/get")
    public ResponseEntity<List<FoodCart>> fetchAllFoods() {
        List<FoodCart> foodCartList = cartService.getfood();
        return ResponseEntity.status(HttpStatus.CREATED).body(foodCartList);
    }

    @DeleteMapping("/delete{cartId}")
    public ResponseEntity<String> deleteFoodid(@PathVariable("cartId") Integer cartId) {
        String deleteFoodId=cartService.deleteId(cartId);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteFoodId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FoodCart> updateFoodcart(@RequestBody FoodCart foodCart, @RequestParam Integer id) {
        FoodCart updateFoodCart=cartService.updateFoodCartId(foodCart, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateFoodCart);
    }
}
