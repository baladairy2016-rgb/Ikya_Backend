package com.vidhuras.hospital_app.Entity;

import aj.org.objectweb.asm.commons.GeneratorAdapter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="emergency_cases")
@Getter
@Setter
public class EmergencyCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;

    private Long patientId;
    private String caseType;
    private String description;

    private String priority;
    private String emergencyStatus;
    private String status;

    private Long assignedDoctorId;
    private Long ambulanceId;

    private LocalDateTime reportedOn;
    private LocalDateTime updatedOn;
}



