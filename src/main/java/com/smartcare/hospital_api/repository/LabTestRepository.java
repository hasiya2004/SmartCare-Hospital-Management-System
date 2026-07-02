package com.smartcare.hospital_api.repository;

import com.smartcare.hospital_api.entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
    List<LabTest> findByPatient_PatientId(Long patientId);
}