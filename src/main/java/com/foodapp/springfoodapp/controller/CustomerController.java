package com.foodapp.springfoodapp.controller;

import com.foodapp.springfoodapp.entiry.Customer;
import com.foodapp.springfoodapp.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v2.prefix}/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer) {
        Customer saveCustomer = customerService.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCustomer);
    }

    @PostMapping("/addMore")
    public ResponseEntity<List<Customer>> addCustomer(@RequestBody @Valid List<Customer> customers) {
        List<Customer> customerList = customerService.saveCustomer(customers);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerList);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Customer>> findAllCustomers() {
        List<Customer> getCustomerList = customerService.getCustomer();
        return new ResponseEntity<>(getCustomerList, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteById(@PathVariable Integer customerId) {
        String delete = customerService.deleteCustomerByid(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(delete);
    }

    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer updateCustomer) {
        Customer customer = customerService.updateCustomer(customerId, updateCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/findEmail/{email}")
    public ResponseEntity<Customer> findByCustomerEmail(@PathVariable String email) {
        Customer customer = customerService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/findMobile/{mobileNumber}")
    public ResponseEntity<Customer> findByMobileNumber(@PathVariable String mobileNumber) {
        Customer customer = customerService.findByMobileNumber(mobileNumber);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}
