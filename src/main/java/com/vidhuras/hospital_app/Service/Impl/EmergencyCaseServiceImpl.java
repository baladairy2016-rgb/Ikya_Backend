package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Dto.EmergencyCaseRequestDto;
import com.vidhuras.hospital_app.Dto.EmergencyCaseResponseDto;
import com.vidhuras.hospital_app.Entity.EmergencyCase;
import com.vidhuras.hospital_app.Repository.EmergencyCaseRepository;
import com.vidhuras.hospital_app.Service.EmergencyCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmergencyCaseServiceImpl implements EmergencyCaseService {

    @Autowired
    private EmergencyCaseRepository repository;

    @Override
    public EmergencyCaseResponseDto createEmergencyCase(EmergencyCaseRequestDto dto) {
        EmergencyCase emergencyCase = new EmergencyCase();
        emergencyCase.setPatientId(dto.getPatientId());
        emergencyCase.setCaseType(dto.getCaseType());
        emergencyCase.setPriority(dto.getPriority());
        emergencyCase.setDescription(dto.getDescription());
        emergencyCase.setAssignedDoctorId(dto.getAssignedDoctorId());
        emergencyCase.setAmbulanceId(dto.getAmbulanceId());
        emergencyCase.setEmergencyStatus(dto.getEmergencyStatus() != null ? dto.getEmergencyStatus() : "PENDING");
        emergencyCase.setStatus("ACTIVE");
        emergencyCase.setReportedOn(LocalDateTime.now());

        EmergencyCase saved = repository.save(emergencyCase);
        return mapToDto(saved);
    }

    @Override
    public List<EmergencyCaseResponseDto> getAllEmergencyCases() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmergencyCaseResponseDto getEmergencyCaseById(Long caseId) {
        EmergencyCase emergencyCase = repository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Emergency case not found with id " + caseId));
        return mapToDto(emergencyCase);
    }

    @Override
    public EmergencyCaseResponseDto updateEmergencyCase(Long caseId, EmergencyCaseRequestDto dto) {
        EmergencyCase emergencyCase = repository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Emergency case not found with id " + caseId));

        if (dto.getPatientId() != null) emergencyCase.setPatientId(dto.getPatientId());
        if (dto.getCaseType() != null) emergencyCase.setCaseType(dto.getCaseType());
        if (dto.getPriority() != null) emergencyCase.setPriority(dto.getPriority());
        if (dto.getDescription() != null) emergencyCase.setDescription(dto.getDescription());
        if (dto.getAssignedDoctorId() != null) emergencyCase.setAssignedDoctorId(dto.getAssignedDoctorId());
        if (dto.getAmbulanceId() != null) emergencyCase.setAmbulanceId(dto.getAmbulanceId());
        if (dto.getEmergencyStatus() != null) emergencyCase.setEmergencyStatus(dto.getEmergencyStatus());

        emergencyCase.setUpdatedOn(LocalDateTime.now());

        EmergencyCase updated = repository.save(emergencyCase);
        return mapToDto(updated);
    }

    @Override
    public void deleteEmergencyCase(Long caseId) {
        EmergencyCase emergencyCase = repository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Emergency case not found with id " + caseId));

        // Soft delete: mark as CANCELLED
        emergencyCase.setEmergencyStatus("CANCELLED");
        emergencyCase.setStatus("INACTIVE");
        emergencyCase.setUpdatedOn(LocalDateTime.now());
        repository.save(emergencyCase);
    }

    // Map Entity to Response DTO
    private EmergencyCaseResponseDto mapToDto(EmergencyCase emergencyCase) {
        EmergencyCaseResponseDto dto = new EmergencyCaseResponseDto();
        dto.setCaseId(emergencyCase.getCaseId());
        dto.setPatientId(emergencyCase.getPatientId());
        dto.setCaseType(emergencyCase.getCaseType());
        dto.setPriority(emergencyCase.getPriority());
        dto.setEmergencyStatus(emergencyCase.getEmergencyStatus());
        dto.setStatus(emergencyCase.getStatus());
        dto.setAssignedDoctorId(emergencyCase.getAssignedDoctorId());
        dto.setAmbulanceId(emergencyCase.getAmbulanceId());
        dto.setReportedOn(emergencyCase.getReportedOn());
        dto.setUpdatedOn(emergencyCase.getUpdatedOn());
        return dto;
    }
}


