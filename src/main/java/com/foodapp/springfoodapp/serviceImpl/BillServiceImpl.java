package com.foodapp.springfoodapp.serviceImpl;


import com.foodapp.springfoodapp.entiry.Bill;
import com.foodapp.springfoodapp.repository.BillRepo;
import com.foodapp.springfoodapp.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepo billRepo;

    @Override
    public List<Bill> getBills() {
        return billRepo.findAll();
    }

    @Override
    public Bill addBill(Bill updateBill) {
        return billRepo.save(updateBill);
    }

    @Override
    public Bill getById(int billId) {
        Optional<Bill> billOpt = billRepo.findByIdQuery(billId);
        if (billOpt.isEmpty()){
            throw new RuntimeException("Bill Not Found");
        }
        return billOpt.get();
//        .orElseThrow(() -> new IllegalStateException("updateBill not found"));
    }

    @Override
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
    public String deleteBill(int billId) {
        billRepo.deleteByIdQuery(billId);
        return "product repo !! " + billId;
    }

    @Override
    public List<Bill> saveAllBills(List<Bill> bills) {
        return billRepo.saveAll(bills);
    }


}
