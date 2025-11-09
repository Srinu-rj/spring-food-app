package com.foodapp.springfoodapp.entiry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private LocalDateTime orderDate;
    private String orderStatus;

    
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private FoodCart cart;

//    @OneToOne(cascade = CascadeType.ALL)
////    @JsonIgnore
//    private Bill bill;
}