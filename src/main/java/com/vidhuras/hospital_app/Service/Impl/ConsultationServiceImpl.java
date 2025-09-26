package com.vidhuras.hospital_app.Service.Impl;


import com.vidhuras.hospital_app.Dto.ConsultationRequestDto;
import com.vidhuras.hospital_app.Dto.ConsultationResponseDto;
import com.vidhuras.hospital_app.Entity.Consultation;
import com.vidhuras.hospital_app.Repository.ConsultationRepository;
import com.vidhuras.hospital_app.Service.AppointmentService;
import com.vidhuras.hospital_app.Service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository repository;

    public ConsultationServiceImpl(ConsultationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConsultationResponseDto addConsultation(ConsultationRequestDto dto) {
        Consultation consultation = new Consultation();
        consultation.setAppointmentId(dto.getAppointmentId());
        consultation.setPatientId(dto.getPatientId());
        consultation.setDoctorId(dto.getDoctorId());
        consultation.setConsultationDate(dto.getConsultationDate());
        consultation.setConsultationTime(dto.getConsultationTime());
        consultation.setSymptoms(dto.getSymptoms());
        consultation.setDiagnosis(dto.getDiagnosis());
        consultation.setPrescription(dto.getPrescription());
        consultation.setTestsRecommended(dto.getTestsRecommended());
        consultation.setFollowupDate(dto.getFollowupDate());
        consultation.setNotes(dto.getNotes());
        consultation.setStatus("ACTIVE");
        consultation.setCreatedOn(LocalDateTime.now());
        consultation.setCreatedBy(dto.getCreatedBy());

        Consultation saved = repository.save(consultation);
        return mapToDto(saved);
    }

    @Override
    public List<ConsultationResponseDto> getAllConsultations() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultationResponseDto getConsultationById(Long consultationId) {
        Consultation consultation = repository.findById(consultationId)
                .orElseThrow(() -> new RuntimeException("Consultation not found with id " + consultationId));
        return mapToDto(consultation);
    }

    @Override
    public ConsultationResponseDto updateConsultation(Long consultationId, ConsultationRequestDto dto) {
        Consultation consultation = repository.findById(consultationId)
                .orElseThrow(() -> new RuntimeException("Consultation not found with id " + consultationId));

        consultation.setConsultationDate(dto.getConsultationDate());
        consultation.setConsultationTime(dto.getConsultationTime());
        consultation.setDoctorId(dto.getDoctorId());
        consultation.setSymptoms(dto.getSymptoms());
        consultation.setDiagnosis(dto.getDiagnosis());
        consultation.setPrescription(dto.getPrescription());
        consultation.setTestsRecommended(dto.getTestsRecommended());
        consultation.setFollowupDate(dto.getFollowupDate());
        consultation.setNotes(dto.getNotes());
        consultation.setModifiedOn(LocalDateTime.now());
        consultation.setModifiedBy(dto.getModifiedBy());

        Consultation updated = repository.save(consultation);
        return mapToDto(updated);
    }

    @Override
    public void deleteConsultation(Long consultationId) {
        Consultation consultation = repository.findById(consultationId)
                .orElseThrow(() -> new RuntimeException("Consultation not found with id " + consultationId));

        consultation.setStatus("INACTIVE");
        consultation.setModifiedOn(LocalDateTime.now());
        repository.save(consultation); // soft delete
    }

    private ConsultationResponseDto mapToDto(Consultation consultation) {
        ConsultationResponseDto dto = new ConsultationResponseDto();
        dto.setConsultationId(consultation.getConsultationId());
        dto.setAppointmentId(consultation.getAppointmentId());
        dto.setPatientId(consultation.getPatientId());
        dto.setDoctorId(consultation.getDoctorId());
        dto.setConsultationDate(consultation.getConsultationDate());
        dto.setConsultationTime(consultation.getConsultationTime());
        dto.setSymptoms(consultation.getSymptoms());
        dto.setDiagnosis(consultation.getDiagnosis());
        dto.setPrescription(consultation.getPrescription());
        dto.setTestsRecommended(consultation.getTestsRecommended());
        dto.setFollowupDate(consultation.getFollowupDate());
        dto.setNotes(consultation.getNotes());
        dto.setStatus(consultation.getStatus());
        dto.setCreatedOn(consultation.getCreatedOn());
        dto.setModifiedOn(consultation.getModifiedOn());
        dto.setCreatedBy(consultation.getCreatedBy());
        dto.setModifiedBy(consultation.getModifiedBy());
        return dto;
    }
}
