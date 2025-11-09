package com.foodapp.springfoodapp.serviceImpl;

import com.foodapp.springfoodapp.entiry.Customer;
import com.foodapp.springfoodapp.repository.CustomerRepo;
import com.foodapp.springfoodapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Customer add(Customer customer) {
        Customer saveCustomer= customerRepo.save(customer);
        return saveCustomer;
    }

    @Override
    public List<Customer> saveCustomer(List<Customer> customers) {
        List<Customer>customerList= customerRepo.saveAll(customers);
        return customerList;
    }

    @Override
    public List<Customer> getCustomer() {
        List<Customer> getAll=customerRepo.findAll();
        return getAll;
    }

    @Override
    public String deleteCustomerByid(Integer customerId) {
        customerRepo.deleteById(customerId);
        return "delete success";
    }

    @Override
    public Customer updateCustomer(Integer customerId, Customer updateCustomer) {
        Customer customer = customerRepo.findById(updateCustomer.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("no customers"));

        customer.setCustomerId(updateCustomer.getCustomerId());
        customer.setFullName(updateCustomer.getFullName());
        customer.setAge(updateCustomer.getAge());
        customer.setEmail(updateCustomer.getEmail());
        customer.setAddress(updateCustomer.getAddress());
        customer.setGender(updateCustomer.getGender());
        customerRepo.save(customer);
        return customer;
    }

    @Override
    public Customer findByEmail(String email) {
        Customer customer=customerRepo.findByEmail(email);
        return customer;
    }

    @Override
    public Customer findByMobileNumber(String mobileNumber) {
        Customer findMobile= customerRepo.findByMobileNumber(mobileNumber);
        return findMobile;
    }
}