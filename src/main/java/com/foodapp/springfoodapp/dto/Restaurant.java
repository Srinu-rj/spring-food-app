package com.foodapp.springfoodapp.dto;

public record Restaurant(
        int restaurantId,
        String restaurantName,
        String managerName,
        String contactNumber,
        Address address
) {
}
