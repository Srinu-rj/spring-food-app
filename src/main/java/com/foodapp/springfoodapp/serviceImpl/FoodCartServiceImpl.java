package com.foodapp.springfoodapp.serviceImpl;


import com.foodapp.springfoodapp.entiry.FoodCart;
import com.foodapp.springfoodapp.repository.FoodCartRepo;
import com.foodapp.springfoodapp.service.FoodCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodCartServiceImpl implements FoodCartService {

    private final FoodCartRepo cartRepo;

    @Override
    public List<FoodCart> savefoods(List<FoodCart> foodCarts) {
        List<FoodCart> saveFood=cartRepo.saveAll(foodCarts);
        return saveFood;
    }

    @Override
    public List<FoodCart> getfood() {
        List<FoodCart> foodCartList=cartRepo.findAll();
        return foodCartList;
    }

    @Override
    public FoodCart savecart(FoodCart foodCart) {
         FoodCart cart=cartRepo.save(foodCart);
         return cart;
    }

    @Override
    public String deleteId(Integer cartId) {
        cartRepo.deleteById(cartId);
        return "cart id deleted";
    }

    @Override
    public FoodCart updateFoodCartId(FoodCart foodCart, Integer id) {
        FoodCart existingCart = cartRepo.findById(foodCart.getCartId())
                .orElseThrow(() -> new RuntimeException("not Found"));
//        FoodCart existingCart1 = cartRepo.findById(id).orElse(null);

        existingCart.setCartId(foodCart.getCartId());
        existingCart.setCartName(foodCart.getCartName());

        return cartRepo.save(existingCart);
    }

    @Override
    public FoodCart getfoodByid(int foodId)  {
//        FoodCart cart=cartRepo.findById(foodId)
//        .orElseThrow(()->new RuntimeException("Not Found"));

        Optional<FoodCart> cartOpt = cartRepo.findById(foodId);
        //todo if cartOpt is empty throw new exception
        if (cartOpt.isEmpty())
            throw new IllegalAccessError("FoodCart Not Found Please Create!");
        return cartOpt.get();
    }


}
