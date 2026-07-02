package com.smartcare.hospital_api.entity;

import com.smartcare.hospital_api.enums.TestStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "lab_tests")
@Data
@NoArgsConstructor
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lab_test_id")
    private Long labTestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "test_name", nullable = false)
    private String testName;

    @Column(name = "test_date", nullable = false)
    private LocalDate testDate;

    @Column(name = "test_result", columnDefinition = "TEXT")
    private String testResult;

    @Column(name = "technician_name", nullable = false)
    private String technicianName;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_status", nullable = false)
    private TestStatus testStatus = TestStatus.Pending;
}