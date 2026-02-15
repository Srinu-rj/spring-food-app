package com.foodapp.springfoodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
//@EnableCaching
public class SpringFoodAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringFoodAppApplication.class, args);
        System.out.println("SPRING BOOT APPLICATION STARTED");
    }


}
