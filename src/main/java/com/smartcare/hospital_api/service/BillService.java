package com.smartcare.hospital_api.service;

import com.smartcare.hospital_api.entity.Bill;
import com.smartcare.hospital_api.enums.PaymentMethod;
import com.smartcare.hospital_api.enums.PaymentStatus;
import com.smartcare.hospital_api.exception.ResourceNotFoundException;
import com.smartcare.hospital_api.repository.BillRepository;
import com.smartcare.hospital_api.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final PatientRepository patientRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));
    }

    @Transactional
    public Bill generateBill(Long patientId, BigDecimal consultation, BigDecimal room, BigDecimal lab, BigDecimal medicine) {
        Bill bill = new Bill();
        bill.setPatient(patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found")));
        bill.setBillDate(LocalDate.now());
        bill.setConsultationCharge(consultation != null ? consultation : BigDecimal.ZERO);
        bill.setRoomCharge(room != null ? room : BigDecimal.ZERO);
        bill.setLabCharge(lab != null ? lab : BigDecimal.ZERO);
        bill.setMedicineCharge(medicine != null ? medicine : BigDecimal.ZERO);
        bill.setPaymentStatus(PaymentStatus.Pending);
        return billRepository.save(bill);
    }

    @Transactional
    public Bill updatePaymentStatus(Long billId, PaymentStatus status, PaymentMethod method) {
        Bill bill = getBillById(billId);
        bill.setPaymentStatus(status);
        bill.setPaymentMethod(method);
        return billRepository.save(bill);
    }

    public List<Bill> getBillsByPatient(Long patientId) {
        return billRepository.findByPatient_Id(patientId);
    }

    @Transactional
    public void deleteBill(Long id) {
        if (!billRepository.existsById(id)) throw new ResourceNotFoundException("Bill not found");
        billRepository.deleteById(id);
    }
}