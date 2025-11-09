package com.foodapp.springfoodapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePage {

    @GetMapping("/")
    public String home() {
        return "hemo"; // Returns index.html from templates folder
    }
}
