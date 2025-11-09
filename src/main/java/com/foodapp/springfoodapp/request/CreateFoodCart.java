package com.foodapp.springfoodapp.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CreateFoodCart {
	public Integer cartId;
	public String categoryName;
}
