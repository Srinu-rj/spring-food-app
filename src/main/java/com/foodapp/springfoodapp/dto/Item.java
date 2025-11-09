package com.foodapp.springfoodapp.dto;


public record Item(int itemId,
                   String itemName,
                   Integer quantity,
                   Double cost
) {
}