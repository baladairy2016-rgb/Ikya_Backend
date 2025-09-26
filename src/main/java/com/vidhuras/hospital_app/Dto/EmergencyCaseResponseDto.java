package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class EmergencyCaseResponseDto {
    private Long caseId;
    private Long patientId;
    private String caseType;
    private String priority;
    private String status;
    private String emergencyStatus;
    private Long assignedDoctorId;
    private Long ambulanceId;
    private LocalDateTime reportedOn;
    private LocalDateTime updatedOn;
}

