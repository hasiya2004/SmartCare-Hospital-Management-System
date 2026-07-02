package com.smartcare.hospital_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "doctors")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "doctor_id")),
        @AttributeOverride(name = "name", column = @Column(name = "doctor_name", nullable = false)),
        @AttributeOverride(name = "contactNumber", column = @Column(name = "contact_number"))
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor extends Person {

    private String qualification;
    private String specialization;

    @Column(name = "consultation_fee", nullable = false)
    private BigDecimal consultationFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // ---- Relationships (back side – ignored) ----
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Treatment> treatments;
}