package com.foodapp.springfoodapp.dto;

public record Address(
        int addressId,
        String area,
        String city,
        String state,
        String country,
        String pinCode

) {
}



