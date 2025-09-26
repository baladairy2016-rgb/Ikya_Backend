package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AppointmentRequestDto {
//    private Long patientId;
    private Long doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String patientName;
    private String phoneNumber;
    private String mode; // ONLINE/OFFLINE
    private String reason;
    private String notes;
    private String meetingLink; // for online appointments
    private String paymentStatus;
    private String paymentReference;
    private String status;
    private String altPhone;
    private String gender;
    private Integer age;
    private String email;
    private String location;
    private PatientRequestDto patient;
    private DoctorSetupRequestDto doctor;
}
