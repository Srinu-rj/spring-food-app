package com.foodapp.springfoodapp.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CreateRestaurant {
	public Integer restaurantId;
	public String restaurantName;
	public String managerName;
	public String contactNumber;
}
