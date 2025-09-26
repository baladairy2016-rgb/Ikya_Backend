package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ConsultationResponseDto {

    private Long consultationId;
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private LocalDate consultationDate;
    private String consultationTime;
    private String symptoms;
    private String diagnosis;
    private String prescription;
    private String testsRecommended;
    private LocalDate followupDate;
    private String notes;
    private String status;
    private String specialities;
    private String location;

    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
}