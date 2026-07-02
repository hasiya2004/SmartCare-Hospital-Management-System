package com.smartcare.hospital_api.repository;

import com.smartcare.hospital_api.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    List<Admission> findByPatient_PatientId(Long patientId);
}