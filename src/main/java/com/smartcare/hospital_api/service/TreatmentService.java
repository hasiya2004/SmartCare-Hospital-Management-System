package com.smartcare.hospital_api.service;

import com.smartcare.hospital_api.entity.Treatment;
import com.smartcare.hospital_api.exception.ResourceNotFoundException;
import com.smartcare.hospital_api.repository.TreatmentRepository;
import com.smartcare.hospital_api.repository.PatientRepository;
import com.smartcare.hospital_api.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public Treatment getTreatmentById(Long id) {
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with id: " + id));
    }

    @Transactional
    public Treatment createTreatment(Treatment treatment) {
        if (treatment.getPatient() != null && treatment.getPatient().getId() != null) {
            treatment.setPatient(patientRepository.findById(treatment.getPatient().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found")));
        }
        if (treatment.getDoctor() != null && treatment.getDoctor().getId() != null) {
            treatment.setDoctor(doctorRepository.findById(treatment.getDoctor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor not found")));
        }
        return treatmentRepository.save(treatment);
    }

    @Transactional
    public Treatment updateTreatment(Long id, Treatment updated) {
        Treatment existing = getTreatmentById(id);
        existing.setDiagnosis(updated.getDiagnosis());
        existing.setPrescription(updated.getPrescription());
        existing.setTreatmentNotes(updated.getTreatmentNotes());
        existing.setTreatmentDate(updated.getTreatmentDate());
        return treatmentRepository.save(existing);
    }

    public List<Treatment> getTreatmentsByPatient(Long patientId) {
        return treatmentRepository.findByPatient_Id(patientId);
    }

    @Transactional
    public void deleteTreatment(Long id) {
        if (!treatmentRepository.existsById(id)) throw new ResourceNotFoundException("Treatment not found");
        treatmentRepository.deleteById(id);
    }
}