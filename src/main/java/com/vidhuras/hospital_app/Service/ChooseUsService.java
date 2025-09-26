package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.ChooseUsResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ChooseUsService {

    List<ChooseUsResponseDto> getAllServices();


    void deleteSlot(Long id);

    ChooseUsResponseDto createService(String serviceName, String description,MultipartFile iconFile) throws IOException;

    ChooseUsResponseDto updateSlot(Long id, String serviceName, String description,MultipartFile iconFile) throws IOException;
}
