package com.smartcare.hospital_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "doctors")
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "doctor_name", nullable = false)),
        @AttributeOverride(name = "contactNumber", column = @Column(name = "contact_number"))
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Doctor extends Person {
    private String qualification;
    private String specialization;

    @Column(name = "consultation_fee", nullable = false)
    private BigDecimal consultationFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}