package com.foodapp.springfoodapp.request;

import com.foodapp.springfoodapp.entiry.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Object data;

}