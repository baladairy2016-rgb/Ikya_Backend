package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Dto.PatientRequestDto;
import com.vidhuras.hospital_app.Dto.PatientResponseDto;
import com.vidhuras.hospital_app.Entity.Patient;
import com.vidhuras.hospital_app.Repository.PatientRepository;
import com.vidhuras.hospital_app.Service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Override
    public PatientResponseDto createPatient(PatientRequestDto request, MultipartFile imageFile) {
        Patient patient = new Patient();

        // Mandatory fields
        patient.setFirstName(request.getFirstName());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setStatus("ACTIVE");
        patient.setCreatedOn(LocalDateTime.now());

        // Optional fields
        if (request.getLastName() != null) patient.setLastName(request.getLastName());
        if (request.getDob() != null) patient.setDob(request.getDob());
        if (request.getEmail() != null) patient.setEmail(request.getEmail());
        if (request.getAddress() != null) patient.setAddress(request.getAddress());
        if (request.getAge() != null) patient.setAge(request.getAge());
        if (request.getBloodGroup() != null) patient.setBloodGroup(request.getBloodGroup());
        if (request.getCity() != null) patient.setCity(request.getCity());
        if (request.getState() != null) patient.setState(request.getState());
        if (request.getPinCode() != null) patient.setPinCode(request.getPinCode());
        if (request.getEmergencyContactName() != null) patient.setEmergencyContactName(request.getEmergencyContactName());
        if (request.getEmergencyContactNumber() != null) patient.setEmergencyContactNumber(request.getEmergencyContactNumber());

        Patient saved = repository.save(patient);
        return mapToResponseDto(saved);
    }

    @Override
    public List<PatientResponseDto> getAllPatients() {
        return repository.findAll().stream()
                .filter(p -> "ACTIVE".equalsIgnoreCase(p.getStatus()))
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponseDto getPatientById(Long patientId) {
        Patient patient = repository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + patientId));
        return mapToResponseDto(patient);
    }

    @Override
    public PatientResponseDto updatePatient(Long patientId, PatientRequestDto request, Object o) {
        Patient patient = repository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + patientId));

        // Mandatory fields
        patient.setFirstName(request.getFirstName());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setModifiedOn(LocalDateTime.now());

        // Optional fields
        if (request.getLastName() != null) patient.setLastName(request.getLastName());
        if (request.getDob() != null) patient.setDob(request.getDob());
        if (request.getEmail() != null) patient.setEmail(request.getEmail());
        if (request.getAddress() != null) patient.setAddress(request.getAddress());
        if (request.getAge() != null) patient.setAge(request.getAge());

        Patient updated = repository.save(patient);
        return mapToResponseDto(updated);
    }

    @Override
    public void deletePatient(Long patientId) {
        Patient patient = repository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + patientId));

        // Soft delete
        patient.setStatus("INACTIVE");
        patient.setModifiedOn(LocalDateTime.now());
        repository.save(patient);
    }

    private PatientResponseDto mapToResponseDto(Patient patient) {
        PatientResponseDto dto = new PatientResponseDto();
        dto.setPatientId(patient.getPatientId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setGender(patient.getGender());
        dto.setDob(patient.getDob());
        dto.setPhone(patient.getPhone());
        dto.setEmail(patient.getEmail());
        dto.setAddress(patient.getAddress());
        dto.setStatus(patient.getStatus());
        dto.setCreatedOn(patient.getCreatedOn());
        dto.setModifiedOn(patient.getModifiedOn());
        return dto;
    }
}
