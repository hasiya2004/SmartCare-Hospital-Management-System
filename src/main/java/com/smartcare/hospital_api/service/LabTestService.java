package com.smartcare.hospital_api.service;

import com.smartcare.hospital_api.entity.LabTest;
import com.smartcare.hospital_api.enums.TestStatus;
import com.smartcare.hospital_api.exception.ResourceNotFoundException;
import com.smartcare.hospital_api.repository.LabTestRepository;
import com.smartcare.hospital_api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LabTestService {
    private final LabTestRepository labTestRepository;
    private final PatientRepository patientRepository;

    public List<LabTest> getAllLabTests() {
        return labTestRepository.findAll();
    }

    public LabTest getLabTestById(Long id) {
        return labTestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lab test not found with id: " + id));
    }

    @Transactional
    public LabTest createLabTest(LabTest labTest) {
        if (labTest.getPatient() != null && labTest.getPatient().getId() != null) {
            labTest.setPatient(patientRepository.findById(labTest.getPatient().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found")));
        }
        return labTestRepository.save(labTest);
    }

    @Transactional
    public LabTest updateLabTest(Long id, LabTest updated) {
        LabTest existing = getLabTestById(id);
        existing.setTestName(updated.getTestName());
        existing.setTestDate(updated.getTestDate());
        existing.setTechnicianName(updated.getTechnicianName());
        return labTestRepository.save(existing);
    }

    @Transactional
    public LabTest updateTestResult(Long id, String result, TestStatus status) {
        LabTest existing = getLabTestById(id);
        existing.setTestResult(result);
        existing.setTestStatus(status);
        return labTestRepository.save(existing);
    }

    public List<LabTest> getLabTestsByPatient(Long patientId) {
        return labTestRepository.findByPatient_Id(patientId);
    }

    @Transactional
    public void deleteLabTest(Long id) {
        if (!labTestRepository.existsById(id)) throw new ResourceNotFoundException("Lab test not found");
        labTestRepository.deleteById(id);
    }
}