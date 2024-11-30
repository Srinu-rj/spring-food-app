package com.foodapp.springfoodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableAsync  //todo for enable -> Thread
@RestController
@RequestMapping("/home")
public class SpringFoodAppApplication {

// TODO ->https://github.com/Srinu-rj/spring-food-app.git
//   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

    @GetMapping("/wellcome")
    public String wellComeMsg() {
        return "------------------------->> WELLCOME TO DEVOPS ------------------------->> ";
    }

    // todo github token  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
    public static void main(String[] args) {
        SpringApplication.run(SpringFoodAppApplication.class, args);
        System.out.println("SPRING-BOOT-APPLICATION");
    }

}
