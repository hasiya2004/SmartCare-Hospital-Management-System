package com.smartcare.hospital_api.service;

import com.smartcare.hospital_api.entity.Appointment;
import com.smartcare.hospital_api.entity.Doctor;
import com.smartcare.hospital_api.entity.Patient;
import com.smartcare.hospital_api.entity.Room;
import com.smartcare.hospital_api.enums.AppointmentStatus;
import com.smartcare.hospital_api.exception.AppointmentConflictException;
import com.smartcare.hospital_api.exception.ResourceNotFoundException;
import com.smartcare.hospital_api.exception.RoomUnavailableException;
import com.smartcare.hospital_api.repository.AppointmentRepository;
import com.smartcare.hospital_api.repository.DoctorRepository;
import com.smartcare.hospital_api.repository.PatientRepository;
import com.smartcare.hospital_api.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final RoomRepository roomRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    @Transactional
    public Appointment bookAppointment(Long patientId, Long doctorId, LocalDate date, LocalTime time, Long roomId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        if (appointmentRepository.existsByDoctorAndAppointmentDateAndAppointmentTime(doctor, date, time)) {
            throw new AppointmentConflictException("Doctor already has an appointment at this date and time.");
        }

        Room room = null;
        if (roomId != null) {
            room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
            if (!room.isAvailable()) {
                throw new RoomUnavailableException("Room is currently unavailable.");
            }
            room.setAvailable(false);
            roomRepository.save(room);
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        appointment.setRoom(room);
        appointment.setStatus(AppointmentStatus.Scheduled);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment cancelAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        appointment.setStatus(AppointmentStatus.Cancelled);
        if (appointment.getRoom() != null) {
            Room room = appointment.getRoom();
            room.setAvailable(true);
            roomRepository.save(room);
        }
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment updateAppointment(Long id, LocalDate date, LocalTime time, Long roomId) {
        Appointment existing = getAppointmentById(id);
        existing.setAppointmentDate(date);
        existing.setAppointmentTime(time);

        if (roomId != null) {
            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
            if (!room.isAvailable()) {
                throw new RoomUnavailableException("Room is currently unavailable.");
            }
            if (existing.getRoom() != null) {
                existing.getRoom().setAvailable(true);
                roomRepository.save(existing.getRoom());
            }
            room.setAvailable(false);
            roomRepository.save(room);
            existing.setRoom(room);
        }

        return appointmentRepository.save(existing);
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctor_IdAndAppointmentDate(doctorId, date);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatient_Id(patientId);
    }

    @Transactional
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) throw new ResourceNotFoundException("Appointment not found");
        appointmentRepository.deleteById(id);
    }
}