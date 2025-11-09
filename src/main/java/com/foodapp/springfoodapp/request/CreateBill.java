package com.foodapp.springfoodapp.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBill {
	public Integer billId;
	public LocalDateTime billDate;
	public Double totalCost;
	public Integer totalItem;
	

}
