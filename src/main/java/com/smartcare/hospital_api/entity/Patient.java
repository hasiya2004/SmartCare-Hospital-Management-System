package com.smartcare.hospital_api.entity;

import com.smartcare.hospital_api.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "full_name", nullable = false)),
        @AttributeOverride(name = "contactNumber", column = @Column(name = "contact_number"))
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
}