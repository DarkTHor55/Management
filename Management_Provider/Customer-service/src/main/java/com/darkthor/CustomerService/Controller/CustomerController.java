package com.darkthor.CustomerService.Controller;

import com.darkthor.CustomerService.Model.Customer;
import com.darkthor.CustomerService.Service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @PostMapping("/crate")
    public ResponseEntity<String> createCustomer(@RequestBody Customer cus,@RequestHeader("loginUserByRole")String role) {
        System.out.println(role);
        Customer customer=customerService.createCustomer(cus);
        if (!Objects.isNull(customer)){
            return ResponseEntity.ok("Customer Created");
        }else {
            return ResponseEntity.badRequest().body("Failed to create customer");
        }
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> ls=customerService.getAllCustomers();
        if (!Objects.isNull(ls))
             return ResponseEntity.ok(customerService.getAllCustomers());
        else
            return ResponseEntity.badRequest().body(new ArrayList<>());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        if(customerService.deleteCustomer(id)) {
            return ResponseEntity.ok("Customer deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer cus) {
        return customerService.getCustomerById(id).map(customer -> {
            customer.setName(cus.getName());
            customer.setEmail(cus.getEmail());
            customer.setPhone(cus.getPhone());
            return ResponseEntity.ok(customerService.updateCustomer(id, customer));
        }).orElse(ResponseEntity.notFound().build());
    }


}
