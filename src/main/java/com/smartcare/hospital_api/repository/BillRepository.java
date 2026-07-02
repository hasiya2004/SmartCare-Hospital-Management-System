package com.smartcare.hospital_api.repository;

import com.smartcare.hospital_api.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByPatient_PatientId(Long patientId);
}