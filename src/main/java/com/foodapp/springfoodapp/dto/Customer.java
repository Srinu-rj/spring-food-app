package com.foodapp.springfoodapp.dto;


public record Customer(

        int customerId,
        String fullName,
        Integer age,
        String gender,
        String mobileNumber,
        String email,
        Address address,
        FoodCart foodCart)
{}

