package com.foodapp.springfoodapp.controller;

import com.foodapp.springfoodapp.entiry.Address;
import com.foodapp.springfoodapp.service.AddressServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {
    private final AddressServices addressServices;

    @GetMapping("/find/all/address/quary")
    public ResponseEntity<List<Address>> findAllByJpql() {
        List<Address> address = addressServices.findAdd();
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping("/add/list/address")
    public ResponseEntity<List<Address>> saveAddress(@RequestBody @Valid List<Address> addresses) {
        List<Address> address = addressServices.saveAddress(addresses);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PostMapping("/save")
    public ResponseEntity<Address> saveAddress(@RequestBody @Valid Address address) {
        Address saveAddressDemo = addressServices.save(address);
        return new ResponseEntity<>(saveAddressDemo, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Address>> getAllAddress() {
        List<Address> address = addressServices.getAll();
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    //TODO THE API SEARCH CITY
    @GetMapping("/get/city/query/{keyWord}")
    public ResponseEntity<Address> getCityByQuery(@PathVariable String keyWord) {
        Address address = addressServices.getCityByQuery(keyWord);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    //TODO THE API FINDS ADDRESS ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> findByAddressId(@PathVariable int id) {
        Address address = addressServices.findByIdAddress(id);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<String> deleteBill(@PathVariable int addressId) {
        String deleteAddress = addressServices.deleteAddress(addressId);
        return new ResponseEntity<>(deleteAddress, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address updateAddress) {
        Address address = addressServices.updateAddress(updateAddress, id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Address> addAddress(@RequestBody String area,
//                                              @RequestBody int address_Id,
//                                              @RequestBody String city,
//                                              @RequestBody String country,
//                                              @RequestBody String pinCode,
//                                              @RequestBody String state) {
//        Address address = addressServices.saveAddAddresByQuerys(area, city,address_Id, country, pinCode, state);
//        return new ResponseEntity<>(address, HttpStatus.CREATED);
//    }
}

