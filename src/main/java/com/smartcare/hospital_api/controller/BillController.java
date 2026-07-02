package com.smartcare.hospital_api.controller;

import com.smartcare.hospital_api.entity.Bill;
import com.smartcare.hospital_api.enums.PaymentMethod;
import com.smartcare.hospital_api.enums.PaymentStatus;
import com.smartcare.hospital_api.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @GetMapping
    public List<Bill> getAll() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Bill getById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @PostMapping("/generate")
    public ResponseEntity<Bill> generate(@RequestParam Long patientId,
                                         @RequestParam(required = false) BigDecimal consultation,
                                         @RequestParam(required = false) BigDecimal room,
                                         @RequestParam(required = false) BigDecimal lab,
                                         @RequestParam(required = false) BigDecimal medicine) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(billService.generateBill(patientId, consultation, room, lab, medicine));
    }

    @PutMapping("/{id}/payment")
    public Bill updatePayment(@PathVariable Long id,
                              @RequestParam PaymentStatus status,
                              @RequestParam PaymentMethod method) {
        return billService.updatePaymentStatus(id, status, method);
    }

    @GetMapping("/patient/{patientId}")
    public List<Bill> getByPatient(@PathVariable Long patientId) {
        return billService.getBillsByPatient(patientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}