package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConsultationRequestDto {

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

    // audit
    private String createdBy;
    private String createdIp;
    private String modifiedBy;
    private String modifiedIp;
}