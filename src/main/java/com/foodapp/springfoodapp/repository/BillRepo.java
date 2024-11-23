package com.foodapp.springfoodapp.repository;

import com.foodapp.springfoodapp.entiry.Bill;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer> {

    @Query(value = "SELECT * FROM bill WHERE bill_id =?1", nativeQuery = true)
    Optional<Bill> findByIdQuery(int billId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM bill WHERE bill_id = ?1", nativeQuery = true)
    void deleteByIdQuery(int billId);
}


