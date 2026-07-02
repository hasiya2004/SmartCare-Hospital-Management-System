package com.smartcare.hospital_api.entity;

import com.smartcare.hospital_api.enums.PaymentMethod;
import com.smartcare.hospital_api.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long billId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "bill_date", nullable = false)
    private LocalDate billDate;

    @Column(name = "consultation_charge", nullable = false)
    private BigDecimal consultationCharge = BigDecimal.ZERO;

    @Column(name = "room_charge", nullable = false)
    private BigDecimal roomCharge = BigDecimal.ZERO;

    @Column(name = "lab_charge", nullable = false)
    private BigDecimal labCharge = BigDecimal.ZERO;

    @Column(name = "medicine_charge", nullable = false)
    private BigDecimal medicineCharge = BigDecimal.ZERO;

    @Column(name = "total_amount", insertable = false, updatable = false)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.Pending;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;
}