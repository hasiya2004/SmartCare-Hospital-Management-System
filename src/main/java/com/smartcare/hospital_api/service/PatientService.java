package com.smartcare.hospital_api.service;

import com.smartcare.hospital_api.entity.Patient;
import com.smartcare.hospital_api.exception.ResourceNotFoundException;
import com.smartcare.hospital_api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    public List<Patient> searchPatients(String name) {
        if (name == null || name.isBlank()) return getAllPatients();
        return patientRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existing = getPatientById(id);
        existing.setName(updatedPatient.getName());
        existing.setDateOfBirth(updatedPatient.getDateOfBirth());
        existing.setGender(updatedPatient.getGender());
        existing.setAddress(updatedPatient.getAddress());
        existing.setContactNumber(updatedPatient.getContactNumber());
        existing.setBloodGroup(updatedPatient.getBloodGroup());
        existing.setEmergencyContact(updatedPatient.getEmergencyContact());
        return patientRepository.save(existing);
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) throw new ResourceNotFoundException("Patient not found");
        patientRepository.deleteById(id);
    }
}