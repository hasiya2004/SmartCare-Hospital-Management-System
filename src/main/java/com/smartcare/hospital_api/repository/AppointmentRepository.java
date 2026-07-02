package com.smartcare.hospital_api.repository;

import com.smartcare.hospital_api.entity.Appointment;
import com.smartcare.hospital_api.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorAndAppointmentDateAndAppointmentTime(Doctor doctor, LocalDate date, LocalTime time);
    List<Appointment> findByDoctor_IdAndAppointmentDate(Long doctorId, LocalDate date);
    List<Appointment> findByPatient_Id(Long patientId);
}