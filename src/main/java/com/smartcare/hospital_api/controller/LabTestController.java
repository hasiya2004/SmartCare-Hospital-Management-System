package com.smartcare.hospital_api.controller;

import com.smartcare.hospital_api.entity.LabTest;
import com.smartcare.hospital_api.enums.TestStatus;
import com.smartcare.hospital_api.service.LabTestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/labtests")
@RequiredArgsConstructor
public class LabTestController {
    private final LabTestService labTestService;

    @GetMapping
    public List<LabTest> getAll() {
        return labTestService.getAllLabTests();
    }

    @GetMapping("/{id}")
    public LabTest getById(@PathVariable Long id) {
        return labTestService.getLabTestById(id);
    }

    @PostMapping
    public ResponseEntity<LabTest> create(@Valid @RequestBody LabTest labTest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(labTestService.createLabTest(labTest));
    }

    @PutMapping("/{id}")
    public LabTest update(@PathVariable Long id, @Valid @RequestBody LabTest labTest) {
        return labTestService.updateLabTest(id, labTest);
    }

    @PutMapping("/{id}/result")
    public LabTest updateResult(@PathVariable Long id,
                                @RequestParam String result,
                                @RequestParam TestStatus status) {
        return labTestService.updateTestResult(id, result, status);
    }

    @GetMapping("/patient/{patientId}")
    public List<LabTest> getByPatient(@PathVariable Long patientId) {
        return labTestService.getLabTestsByPatient(patientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        labTestService.deleteLabTest(id);
        return ResponseEntity.noContent().build();
    }
}