package com.smartcare.hospital_api.service;

import com.smartcare.hospital_api.entity.Doctor;
import com.smartcare.hospital_api.exception.ResourceNotFoundException;
import com.smartcare.hospital_api.repository.DoctorRepository;
import com.smartcare.hospital_api.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    @Transactional
    public Doctor createDoctor(Doctor doctor) {
        if (doctor.getDepartment() != null && doctor.getDepartment().getDepartmentId() != null) {
            doctor.setDepartment(departmentRepository.findById(doctor.getDepartment().getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found")));
        }
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor assignDepartment(Long doctorId, Long deptId) {
        Doctor doctor = getDoctorById(doctorId);
        doctor.setDepartment(departmentRepository.findById(deptId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found")));
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existing = getDoctorById(id);
        existing.setName(updatedDoctor.getName());
        existing.setQualification(updatedDoctor.getQualification());
        existing.setSpecialization(updatedDoctor.getSpecialization());
        existing.setContactNumber(updatedDoctor.getContactNumber());
        existing.setConsultationFee(updatedDoctor.getConsultationFee());
        if (updatedDoctor.getDepartment() != null) {
            existing.setDepartment(updatedDoctor.getDepartment());
        }
        return doctorRepository.save(existing);
    }

    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) throw new ResourceNotFoundException("Doctor not found");
        doctorRepository.deleteById(id);
    }

    public List<Doctor> searchBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationContainingIgnoreCase(specialization);
    }
}