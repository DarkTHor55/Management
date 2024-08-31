package com.darkthor.BillingService.Service;

import com.darkthor.BillingService.Model.Bill;

import java.util.List;

public interface IBillService {
    Bill createBill(Bill bill);
    Bill getBillById(Long id);
    Bill updateBill(Long id, Bill bill);
    boolean deleteBill(Long id);
    List<Bill> getAllBills();
}
