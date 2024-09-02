package com.darkthor.PlayrollService.Controller;

import com.darkthor.PlayrollService.Model.Payroll;
import com.darkthor.PlayrollService.Service.PayrollServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users/payroll")
@RestController
public class PayrollController {
    private final PayrollServiceImpl payrollService;

    @PostMapping("/create")
    ResponseEntity<String>createPayroll(@RequestBody Payroll payroll){
        Payroll p = payrollService.createPayroll(payroll);
        if (!p.equals(null)){
            return ResponseEntity.ok("Payroll created successfully");
        }
        return ResponseEntity.badRequest().body("Failed to create payroll");
    }
    @GetMapping("/{id}")
    ResponseEntity<Payroll> getPayrollById(@PathVariable Long id){
        Payroll p = payrollService.getPayrollById(id);
        if (!p.equals(null)){
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    ResponseEntity<List<Payroll>> getAllPayrolls(){
        List<Payroll> l=payrollService.getAllPayrolls();
        if (!l.isEmpty()){
            return ResponseEntity.ok(l);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deletePayroll(@PathVariable Long id){
        if(payrollService.deletePayroll(id)){
            return ResponseEntity.ok("Payroll deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    ResponseEntity<Payroll> updatePayroll(@PathVariable Long id, @RequestBody Payroll payroll){
        Payroll p = payrollService.updatePayroll(id,payroll);
        if (!p.equals(null)){
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.notFound().build();
    }
}
