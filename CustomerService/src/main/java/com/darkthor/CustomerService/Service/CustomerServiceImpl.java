package com.darkthor.CustomerService.Service;

import com.darkthor.CustomerService.Model.Customer;
import com.darkthor.CustomerService.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomerService{
    private final CustomerRepository repository;
    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer c=repository.findById(id).orElse(null);
        if (!Objects.isNull(c)) {
            c.setEmail(customer.getEmail());
            c.setName(customer.getName());
            c.setPhone(customer.getPhone());
            return repository.save(c);

        }else{
            return null;

        }

    }

    @Override
    public boolean deleteCustomer(Long id) {
        Optional<Customer> c=repository.findById(id);
        if(!Objects.isNull(c)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
