package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.AboutResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AboutService {
    AboutResponseDto addAbout(String title,String subTitle, String description, MultipartFile imageFile);

    AboutResponseDto updateUs(Long id, String title,String subTitle, String description, MultipartFile imageFile);

    List<AboutResponseDto> getAllServices();

    void deleteSlot(Long id);
}
