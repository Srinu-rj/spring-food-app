package com.foodapp.springfoodapp.repository;

import com.foodapp.springfoodapp.entiry.Restaurant;
import jakarta.transaction.Transactional;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
//    @Query("select  r from restaurant r where r.manager_name = :manager_name")
    @Query(value = "SELECT * FROM address WHERE manager_name: ",nativeQuery = true)
    Restaurant managerName(String managerName);

    @Query(value = "SELECT * FROM address ", nativeQuery = true)
    List<Restaurant> findAllRestaurantByQuery();

    //    @Query("DELETE FROM bill WHERE restaurant_id ")
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bill WHERE restaurant_id = ?1", nativeQuery = true)
    void deleteByQuery(int restaurantId);

}
