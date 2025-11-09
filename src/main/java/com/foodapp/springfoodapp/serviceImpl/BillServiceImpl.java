package com.foodapp.springfoodapp.serviceImpl;


import com.foodapp.springfoodapp.entiry.Bill;
import com.foodapp.springfoodapp.repository.BillRepo;
import com.foodapp.springfoodapp.service.BillService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepo billRepo;

    @Override
    @Transactional
    public List<Bill> getBills() {
        return billRepo.findAll();
    }

    @Override
    @Transactional
    public Bill addBill(Bill updateBill) {
        return billRepo.save(updateBill);
    }

    @Override
    @Transactional
    public Bill getById(int billId) {
        Optional<Bill> billOpt = billRepo.findByIdQuery(billId);
        if (billOpt.isEmpty()) {
            throw new RuntimeException("Bill Not Found");
        }
        return billOpt.get();
//        .orElseThrow(() -> new IllegalStateException("updateBill not found"));
    }

    @Override
    @Transactional
    public Bill updateBill(Integer id, Bill updateBill) {
        Bill existBill = billRepo.findByIdQuery(updateBill.getBillId())
                .orElseThrow(() -> new RuntimeException("Can't Find billId"));

        existBill.setBillId(existBill.getBillId());
        existBill.setBillDate(updateBill.getBillDate());
        existBill.setOrder(updateBill.getOrder());
        existBill.setTotalCost(updateBill.getTotalCost());

        return billRepo.save(existBill);
    }

    @Override
    @Transactional
    public Bill deleteBill(int billId) {
//        return billRepo.deleteByIdQuery(billId);
        return Optional.ofNullable(billRepo.deleteByIdQuery(billId))
                .orElseThrow(() -> new RuntimeException("Bill not found for deletion with id: " + billId));
    }

    @Override
    @Transactional
    public List<Bill> saveAllBills(List<Bill> bills) {
        return bills.stream()
                .map(billRepo::save)
                .collect(Collectors.toList());
    }


}
