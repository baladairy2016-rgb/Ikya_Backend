package com.vidhuras.hospital_app.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id")
    private Long consultationId;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "consultation_date", nullable = false)
    private LocalDate consultationDate;

    @Column(name = "consultation_time", nullable = false)
    private String consultationTime;

    @Column(name = "symptoms", columnDefinition = "TEXT")
    private String symptoms;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "prescription", columnDefinition = "TEXT")
    private String prescription;

    @Column(name = "tests_recommended", columnDefinition = "TEXT")
    private String testsRecommended;

    @Column(name = "followup_date")
    private LocalDate followupDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "status", length = 50)
    private String status = "ACTIVE";

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;
}