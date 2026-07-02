package com.smartcare.hospital_api.controller;

import com.smartcare.hospital_api.entity.Treatment;
import com.smartcare.hospital_api.service.TreatmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/treatments")
@RequiredArgsConstructor
public class TreatmentController {
    private final TreatmentService treatmentService;

    @GetMapping
    public List<Treatment> getAll() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/{id}")
    public Treatment getById(@PathVariable Long id) {
        return treatmentService.getTreatmentById(id);
    }

    @PostMapping
    public ResponseEntity<Treatment> create(@Valid @RequestBody Treatment treatment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(treatmentService.createTreatment(treatment));
    }

    @PutMapping("/{id}")
    public Treatment update(@PathVariable Long id, @Valid @RequestBody Treatment treatment) {
        return treatmentService.updateTreatment(id, treatment);
    }

    @GetMapping("/patient/{patientId}")
    public List<Treatment> getByPatient(@PathVariable Long patientId) {
        return treatmentService.getTreatmentsByPatient(patientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }
}