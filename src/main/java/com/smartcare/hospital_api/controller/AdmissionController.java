package com.smartcare.hospital_api.controller;

import com.smartcare.hospital_api.entity.Admission;
import com.smartcare.hospital_api.service.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admissions")
@RequiredArgsConstructor
public class AdmissionController {
    private final AdmissionService admissionService;

    @GetMapping
    public List<Admission> getAll() {
        return admissionService.getAllAdmissions();
    }

    @GetMapping("/{id}")
    public Admission getById(@PathVariable Long id) {
        return admissionService.getAdmissionById(id);
    }

    @PostMapping("/admit")
    public ResponseEntity<Admission> admit(@RequestParam Long patientId,
                                           @RequestParam Long roomId,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate admitDate) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(admissionService.admitPatient(patientId, roomId, admitDate));
    }

    @PutMapping("/{id}/discharge")
    public Admission discharge(@PathVariable Long id,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dischargeDate) {
        return admissionService.dischargePatient(id, dischargeDate);
    }

    @GetMapping("/patient/{patientId}")
    public List<Admission> getByPatient(@PathVariable Long patientId) {
        return admissionService.getAdmissionsByPatient(patientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        admissionService.deleteAdmission(id);
        return ResponseEntity.noContent().build();
    }
}