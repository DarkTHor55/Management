package com.darkthor.PlayrollService.Repository;

import com.darkthor.PlayrollService.Model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository extends JpaRepository<Payroll,Long> {
}
