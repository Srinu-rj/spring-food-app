package com.foodapp.springfoodapp.dto;

import java.time.LocalDateTime;

public record OrderDetails(
        Integer orderId,
        LocalDateTime orderDate,
        String orderStatus
) {

}


