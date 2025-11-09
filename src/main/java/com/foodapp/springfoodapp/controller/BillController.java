package com.foodapp.springfoodapp.controller;


import com.foodapp.springfoodapp.entiry.Bill;
import com.foodapp.springfoodapp.exception.ResourceNotFoundException;
import com.foodapp.springfoodapp.request.ApiResponse;
import com.foodapp.springfoodapp.service.BillService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequestMapping("${api.v1.prefix}/bill")
@Slf4j
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/add")
    public ResponseEntity<?> addBill(@RequestBody @Valid Bill bill) {
        Bill saveBill = billService.addBill(bill);
        return new ResponseEntity<>(saveBill, HttpStatus.CREATED);
    }

    @PostMapping("/addList")
    public ResponseEntity<List<Bill>> addBillsList(@RequestBody @Valid List<Bill> bills) {
        List<Bill> billList = billService.saveAllBills(bills);
        return new ResponseEntity<>(billList, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Bill>> findAllBills() {
        List<Bill> getAllBills = billService.getBills();
        return new ResponseEntity<>(getAllBills, HttpStatus.CREATED);
    }

    @GetMapping("/bill/{billId}")
    public ResponseEntity<Bill> findById(@PathVariable("billId") int billId) {
        Bill bill = billService.getById(billId);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Integer id, @RequestBody Bill updateBill) {
        Bill bill = billService.updateBill(id, updateBill);
        return new ResponseEntity<>(bill, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{billId}")
    public ResponseEntity<ApiResponse> deleteBill(@PathVariable int billId) {

        try {
            Bill deleteBill = billService.deleteBill(billId);
            return ResponseEntity.ok(new ApiResponse("Deleted Bill Success", deleteBill));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

}
