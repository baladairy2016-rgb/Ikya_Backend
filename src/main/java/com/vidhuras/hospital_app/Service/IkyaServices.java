package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.IkyaServiceResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IkyaServices {
    IkyaServiceResponseDto addDoctors(String serviceName, String description, int popularService, MultipartFile imageFile);

    List<IkyaServiceResponseDto> getAllDoctors();

    IkyaServiceResponseDto updateDoctor(Long id, String serviceName, String description, int popularService, MultipartFile imageFile);

    void deleteDoctor(Long id);
}
