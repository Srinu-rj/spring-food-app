package com.foodapp.springfoodapp.entiry;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
@Entity
@DynamicInsert
@DynamicUpdate
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int itemId;
    @NotNull(message = "should not be Null  ")
    private String itemName;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double cost;

}