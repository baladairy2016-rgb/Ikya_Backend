package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.AmbulanceRequestDto;
import com.vidhuras.hospital_app.Dto.AmbulanceResponseDto;

import java.util.List;

public interface AmbulanceService {
    AmbulanceResponseDto createService(AmbulanceRequestDto dto);

    AmbulanceResponseDto updateAmbulance(Long id, AmbulanceRequestDto dto);

    AmbulanceResponseDto getAmbulanceById(Long id);

    List<AmbulanceResponseDto> getAllAmbulances();

    void deleteAmbulance(Long id);
}
