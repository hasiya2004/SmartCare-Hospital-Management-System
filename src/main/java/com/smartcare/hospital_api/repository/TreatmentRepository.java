package com.smartcare.hospital_api.repository;

import com.smartcare.hospital_api.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByPatient_Id(Long patientId);
}