package com.darkthor.PlayrollService.Service;

import com.darkthor.PlayrollService.Model.Payroll;

import java.util.List;

public interface IPayrollService {
    Payroll createPayroll(Payroll payroll);
    Payroll getPayrollById(Long id);
    Payroll updatePayroll(Long id, Payroll payroll);
    boolean deletePayroll(Long id);
    List<Payroll> getAllPayrolls();
}
