package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.SliderRequestDto;
import com.vidhuras.hospital_app.Dto.SliderResponseDto;
import com.vidhuras.hospital_app.Entity.Slider;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SliderService {
    Slider saveSlider(String title, String subTitle, MultipartFile image) throws IOException;

    SliderResponseDto addSlider(SliderRequestDto dto);

    List<SliderResponseDto> getAllSliders();

    SliderResponseDto getSliderById(Long id);

    String saveFile(MultipartFile image) throws IOException;

    void softDeleteSlider(Long id);

    SliderResponseDto updateSliderFull(Long id, SliderRequestDto dto);

    SliderResponseDto updateSliderPartial(Long id, SliderRequestDto dto);
}
