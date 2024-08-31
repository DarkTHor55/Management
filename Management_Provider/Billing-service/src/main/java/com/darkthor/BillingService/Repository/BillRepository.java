package com.darkthor.BillingService.Repository;

import com.darkthor.BillingService.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
}
