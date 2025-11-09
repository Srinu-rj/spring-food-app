package com.foodapp.springfoodapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class Category {
	public Integer categoryId;
	public String categoryName;
}
