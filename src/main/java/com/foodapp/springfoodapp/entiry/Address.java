package com.foodapp.springfoodapp.entiry;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
@DynamicInsert
@DynamicUpdate
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int addressId;
    @NotNull
    public String area;
    @NotNull(message = "shouldn't be Null City")
    public String city;
    @NotNull(message = "shouldn't be Null State")
    public String state;
    @NotNull(message = "shouldn't be Null Country")
    public String country;
    @Column(nullable = false, unique = true)
    public String pinCode;
}

