package com.darkthor.BillingService.Controller;

import com.darkthor.BillingService.Model.Bill;
import com.darkthor.BillingService.Service.BillServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users/bill")
@RequiredArgsConstructor
public class BillController {
    private  final BillServiceImpl billService;
    @PostMapping
    public ResponseEntity<String>createBill(@RequestBody Bill bill ){
        Bill b =billService.createBill(bill);
        if (!Objects.isNull(b)){
            return ResponseEntity.ok("Bill Created");
        }else{
            return ResponseEntity.badRequest().body("Failed to create bill");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Bill b = billService.getBillById(id);
        if (Objects.nonNull(b)) {
            return ResponseEntity.ok(b);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills(){
        List<Bill> l=billService.getAllBills();
        if (!Objects.isNull(l)){
            return ResponseEntity.ok(l);
        }else{
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Long id){
        if(billService.deleteBill(id)){
            return ResponseEntity.ok("Bill deleted successfully");
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill){
        Bill b = billService.updateBill(id,bill);
        if (!Objects.isNull(b)){
            return ResponseEntity.ok(billService.updateBill(id,bill));
        } else{
            return ResponseEntity.notFound().build();
        }
    }


}
