package com.darkthor.BillingService.Service;

import com.darkthor.BillingService.Model.Bill;
import com.darkthor.BillingService.Repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements IBillService{
    private final BillRepository billRepository;
    @Override
    public Bill createBill(Bill bill) {
        bill.setPaymentDate(new Date());
        return billRepository.save(bill);
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public Bill updateBill(Long id, Bill bill) {
        return billRepository.findById(id)
                .map(existingBill -> {
                    existingBill.setCustomerId(bill.getCustomerId());
                    existingBill.setPaymentMethod(bill.getPaymentMethod());
                    existingBill.setTotalAmount(bill.getTotalAmount());
                    return billRepository.save(existingBill);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteBill(Long id) {
        Bill bill1 = billRepository.findById(id).orElse(null);
        if (!Objects.isNull(bill1)) {
            billRepository.delete(bill1);
            return true;
        }
        return false;
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
}
