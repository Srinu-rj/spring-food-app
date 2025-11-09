package com.foodapp.springfoodapp.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CreateItem {
	public Integer itemId;
	public String itemName;
	public Integer quantity;
	public Double cost;
}
