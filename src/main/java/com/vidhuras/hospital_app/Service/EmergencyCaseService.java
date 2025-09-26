package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.EmergencyCaseRequestDto;
import com.vidhuras.hospital_app.Dto.EmergencyCaseResponseDto;

import java.util.List;

public interface EmergencyCaseService {
    EmergencyCaseResponseDto createEmergencyCase(EmergencyCaseRequestDto dto);

    List<EmergencyCaseResponseDto> getAllEmergencyCases();

    EmergencyCaseResponseDto getEmergencyCaseById(Long id);

    EmergencyCaseResponseDto updateEmergencyCase(Long id, EmergencyCaseRequestDto dto);

    void deleteEmergencyCase(Long id);
}
