package com.foodapp.springfoodapp.service;

import com.foodapp.springfoodapp.entiry.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

	Customer add(Customer customer);

	List<Customer> saveCustomer(List<Customer> customers);

	List<Customer> getCustomer();

	String deleteCustomerByid(Integer customerId);
//	List<Customer>getAllCustomerByNative();

	Customer updateCustomer(Integer customerId, Customer updateCustomer);
	Customer findByEmail(String email);
	Customer findByMobileNumber (String mobileNumber);

}
