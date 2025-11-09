package com.foodapp.springfoodapp.request;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CreateOrder {
	public Integer orderId;
	public LocalDateTime orderDate;
	public String orderStatus;
}
