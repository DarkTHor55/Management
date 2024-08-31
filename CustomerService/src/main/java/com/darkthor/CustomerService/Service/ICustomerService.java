package com.darkthor.CustomerService.Service;

import com.darkthor.CustomerService.Model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    public List<Customer> getAllCustomers() ;
    public Optional<Customer> getCustomerById(Long id) ;

    public Customer createCustomer(Customer customer) ;

    public Customer updateCustomer(Long id, Customer customer) ;

    public boolean deleteCustomer(Long id) ;
}
