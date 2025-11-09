package com.foodapp.springfoodapp.repository;

import com.foodapp.springfoodapp.entiry.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,Integer> {
}
