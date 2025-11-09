package com.foodapp.springfoodapp.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAddress {
	public Integer addressId;
	public String area;
	public String city;
	public String state;
	public String country;
	public String pinCode;
}
