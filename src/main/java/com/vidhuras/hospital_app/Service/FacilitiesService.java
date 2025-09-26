package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.FacilitiesRequestDto;
import com.vidhuras.hospital_app.Dto.FacilitiesResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FacilitiesService {





    List<FacilitiesResponseDto> getAllFacilities();

    FacilitiesResponseDto getFacilityById(Long id);

    void deleteFacility(Long id);

    FacilitiesResponseDto addFacility(FacilitiesRequestDto dto, String createdBy, MultipartFile imageFile, MultipartFile iconFile) throws IOException;

    FacilitiesResponseDto updateFacility(Long id, FacilitiesRequestDto dto, String modifiedBy, MultipartFile imageFile, MultipartFile iconFile) throws IOException;
}
