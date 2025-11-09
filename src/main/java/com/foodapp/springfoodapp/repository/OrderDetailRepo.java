package com.foodapp.springfoodapp.repository;

import com.foodapp.springfoodapp.entiry.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetails,Integer> {
}
