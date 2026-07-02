package com.smartcare.hospital_api.service;

import com.smartcare.hospital_api.entity.Admission;
import com.smartcare.hospital_api.entity.Patient;
import com.smartcare.hospital_api.entity.Room;
import com.smartcare.hospital_api.exception.ResourceNotFoundException;
import com.smartcare.hospital_api.exception.RoomUnavailableException;
import com.smartcare.hospital_api.repository.AdmissionRepository;
import com.smartcare.hospital_api.repository.PatientRepository;
import com.smartcare.hospital_api.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmissionService {
    private final AdmissionRepository admissionRepository;
    private final PatientRepository patientRepository;
    private final RoomRepository roomRepository;

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    public Admission getAdmissionById(Long id) {
        return admissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + id));
    }

    @Transactional
    public Admission admitPatient(Long patientId, Long roomId, LocalDate admitDate) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (!room.isAvailable()) {
            throw new RoomUnavailableException("Room is currently occupied.");
        }

        room.setAvailable(false);
        roomRepository.save(room);

        Admission admission = new Admission();
        admission.setPatient(patient);
        admission.setRoom(room);
        admission.setAdmitDate(admitDate);

        return admissionRepository.save(admission);
    }

    @Transactional
    public Admission dischargePatient(Long admissionId, LocalDate dischargeDate) {
        Admission admission = getAdmissionById(admissionId);
        admission.setDischargeDate(dischargeDate);

        Room room = admission.getRoom();
        room.setAvailable(true);
        roomRepository.save(room);

        return admissionRepository.save(admission);
    }

    public List<Admission> getAdmissionsByPatient(Long patientId) {
        return admissionRepository.findByPatient_Id(patientId);
    }

    @Transactional
    public void deleteAdmission(Long id) {
        if (!admissionRepository.existsById(id)) throw new ResourceNotFoundException("Admission not found");
        admissionRepository.deleteById(id);
    }
}