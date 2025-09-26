package com.vidhuras.hospital_app.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @Column(name = "patient_id", nullable = true)
    private Long patientId;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "appointment_time")
    private String appointmentTime;

    @Column(name = "mode")
    private String mode; // e.g., ONLINE / OFFLINE

    @Column(name = "reason")
    private String reason;

    @Column(name = "notes")
    private String notes;

    // Online booking fields
    @Column(name = "meeting_link")
    private String meetingLink;

    @Column(name = "payment_status")
    private String paymentStatus; // PENDING / PAID / FAILED

    @Column(name = "payment_reference")
    private String paymentReference;

    @Column(name = "status")
    private String status; // ACTIVE / INACTIVE

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_ip")
    private String createdIp;

    @Column(name = "modified_ip")
    private String modifiedIp;

    @Column(name = "alt_phone")
    private String altPhone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "location")
    private String location;

    @Column(name = "fee")
    private int fee;
}

