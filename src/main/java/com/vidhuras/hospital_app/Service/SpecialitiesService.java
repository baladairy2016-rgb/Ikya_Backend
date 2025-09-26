package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.SpecialitiesRequestDto;
import com.vidhuras.hospital_app.Dto.SpecialitiesResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SpecialitiesService {


    List<SpecialitiesResponseDto> getAllFacilities();

    SpecialitiesResponseDto getFacilityById(Long id);

    void deleteFacility(Long id);

//    SpecialitiesResponseDto addFacility(SpecialitiesRequestDto dto, String createdBy, MultipartFile iconFile) throws IOException;

//    SpecialitiesResponseDto updateFacility(Long id, SpecialitiesRequestDto dto, String modifiedBy, MultipartFile iconFile) throws IOException;

    SpecialitiesResponseDto addFacility(SpecialitiesRequestDto dto, String createdBy, MultipartFile iconFile, MultipartFile image, MultipartFile image1) throws IOException;

    SpecialitiesResponseDto updateFacility(Long id, SpecialitiesRequestDto dto, String modifiedBy, MultipartFile iconFile, MultipartFile image, MultipartFile image1)  throws IOException;
}
