package com.smartcare.hospital_api.entity;

import com.smartcare.hospital_api.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "patient_id")),
        @AttributeOverride(name = "name", column = @Column(name = "full_name", nullable = false)),
        @AttributeOverride(name = "contactNumber", column = @Column(name = "contact_number"))
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient extends Person {

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String address;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    // ---- Relationships (back side – ignored to avoid recursion) ----
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Admission> admissions;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Treatment> treatments;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<LabTest> labTests;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Bill> bills;
}