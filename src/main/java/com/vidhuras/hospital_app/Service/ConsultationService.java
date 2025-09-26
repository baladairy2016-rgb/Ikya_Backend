package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.ConsultationRequestDto;
import com.vidhuras.hospital_app.Dto.ConsultationResponseDto;

import java.util.List;

public interface ConsultationService {
    ConsultationResponseDto addConsultation(ConsultationRequestDto dto);

    List<ConsultationResponseDto> getAllConsultations();

    ConsultationResponseDto getConsultationById(Long consultationId);

    ConsultationResponseDto updateConsultation(Long consultationId, ConsultationRequestDto dto);

    void deleteConsultation(Long consultationId);
}
