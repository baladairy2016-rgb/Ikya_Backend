package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmergencyCaseRequestDto {
    private Long patientId;
    private String caseType;
    private String priority; // HIGH, MEDIUM, LOW
    private String description;
    private Long assignedDoctorId;
    private Long ambulanceId;
    private String emergencyStatus; // PENDING, ASSIGNED, COMPLETED, CANCELLED
    private String status; // ACTIVE,INACTIVE
}

