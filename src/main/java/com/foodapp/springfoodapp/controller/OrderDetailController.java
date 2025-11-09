package com.foodapp.springfoodapp.controller;


import com.foodapp.springfoodapp.entiry.OrderDetails;
import com.foodapp.springfoodapp.service.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1.prefix}/order")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;


    @PostMapping("/add")
    public ResponseEntity<OrderDetails> addOrderDetails(@RequestBody @Valid OrderDetails details) {
        OrderDetails saveOretails = orderDetailService.saveOrder(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveOretails);
    }

    @GetMapping("/get")
    public ResponseEntity<List<OrderDetails>> fetchAllOrders() {
        List<OrderDetails> getOrderDetail = orderDetailService.getOrders();
        return new ResponseEntity<>(getOrderDetail, HttpStatus.CREATED);
    }

    @PostMapping("/addMore")
    public ResponseEntity<List<OrderDetails>> saveOrders(@RequestBody @Valid List<OrderDetails> orderDetails) {
        List<OrderDetails> orderDetailsList = orderDetailService.saveOrders(orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsList);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Integer orderId) {
        String deleteOrder = orderDetailService.deleteorder(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteOrder);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDetails> updateOrder(@RequestBody OrderDetails updateOrderDetails, @PathVariable int id) {
        OrderDetails updateDetails = orderDetailService.updateOrder(updateOrderDetails, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateDetails);
    }

}
