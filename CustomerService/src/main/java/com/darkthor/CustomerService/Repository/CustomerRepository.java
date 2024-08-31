package com.darkthor.CustomerService.Repository;

import com.darkthor.CustomerService.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}