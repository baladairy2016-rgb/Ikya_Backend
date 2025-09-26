package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentResponseDto {
    private Long appointmentId;
    private Long doctorId;
    private String patientName;
    private String phoneNumber;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String mode;
//    private String reason;
    private String status;
//    private String notes;
//    private Long patientId;
    // Online booking fields
//    private String meetingLink;
    private String paymentStatus; // optional, default PENDING
    private String paymentReference;

    private LocalDateTime createdOn;
    private String createdBy;
    private String createdIp;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
    private String modifiedIp;
    private String altPhone;
    private String gender;
    private Integer age;
    private String email;
    private String location;
}

