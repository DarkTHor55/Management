package com.darkthor.PlayrollService.Service;

import com.darkthor.PlayrollService.Model.Payroll;
import com.darkthor.PlayrollService.Repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements IPayrollService{
    private final PayrollRepository repository;
    @Override
    public Payroll createPayroll(Payroll payroll) {
        payroll.setPayDate(new Date());
        return repository.save(payroll);
    }

    @Override
    public Payroll getPayrollById(Long id) {
        return repository.findById(id).orElseThrow(null);
    }

    @Override
    public Payroll updatePayroll(Long id, Payroll payroll) {
        Payroll p=repository.findById(id).orElseThrow(null);
        if (!Objects.isNull(p)){
            p.setAllowances(payroll.getAllowances());
            p.setBasicSalary(payroll.getBasicSalary());
            p.setBonuses(payroll.getBonuses());
            p.setDeductions(payroll.getDeductions());
            return repository.save(p);
        }
        return null;
    }

    @Override
    public boolean deletePayroll(Long id) {
        Payroll p=repository.findById(id).orElseThrow(null);
        if(!Objects.isNull(p)) {
            repository.delete(p);
            return true;
        }
        return false;
    }

    @Override
    public List<Payroll> getAllPayrolls() {
        return repository.findAll();
    }
}
