package com.foodapp.springfoodapp.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CreateCustomer {
	public Integer customerId;
	public String fullName;
	public Integer age;
	public String gender;
	public String mobileNumber;
	public String email;
}
