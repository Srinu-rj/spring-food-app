package com.foodapp.springfoodapp.entiry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Restaurant")
@Entity
@DynamicInsert
@DynamicUpdate
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int restaurantId;
    @NotNull(message = "RestaurantName shouldn't be Null ")
    private String restaurantName;
    @NotNull(message = "ManagerName shouldn't be Null ")
    private String managerName;
    @NotNull
    @Pattern(regexp = "^\\d{10}$")
    private String contactNumber;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(targetEntity = FoodCart.class, cascade = CascadeType.ALL)
    private List<FoodCart> foodCart;


}