package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Dto.SliderRequestDto;
import com.vidhuras.hospital_app.Dto.SliderResponseDto;
import com.vidhuras.hospital_app.Entity.Slider;
import com.vidhuras.hospital_app.Repository.SliderRepository;
import com.vidhuras.hospital_app.Service.SliderService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SliderServiceImpl implements SliderService {

    private final SliderRepository repository;

    private final String UPLOAD_DIR = "uploads/";

    public SliderServiceImpl(SliderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Slider saveSlider(String title, String subTitle, MultipartFile image) throws IOException {
        String publicPath = saveFile(image);

        Slider entity = new Slider();
        entity.setTitle(title);
        entity.setSubTitle(subTitle);
        entity.setImages(publicPath);        // ← store "/uploads/<file>"
        entity.setCreatedOn(LocalDateTime.now());
        entity.setStatus("ACTIVE");
        return repository.save(entity);
    }

    @Override
    public SliderResponseDto addSlider(SliderRequestDto dto) {
        Slider entity = new Slider();
        entity.setTitle(dto.getTitle());
        entity.setSubTitle(dto.getSubTitle());
        entity.setImages(dto.getImages());   // controller already gave public path
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : "ACTIVE");
        entity.setCreatedOn(LocalDateTime.now());
        entity.setCreatedIp("127.0.0.1");
        entity.setCreatedAt("system");

        Slider saved = repository.save(entity);

        SliderResponseDto res = new SliderResponseDto();
        res.setId(saved.getId());
        res.setTitle(saved.getTitle());
        res.setSubTitle(saved.getSubTitle());
        res.setImages(saved.getImages());
        res.setCreatedOn(saved.getCreatedOn());
        res.setCreatedIp(saved.getCreatedIp());
        res.setCreatedAt(saved.getCreatedAt());
        res.setStatus(saved.getStatus());
        return res;
    }


    @Override
    public String saveFile(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) return null;

        // Validate type (optional but recommended)
        String ct = image.getContentType();
        if (ct == null || !(ct.equalsIgnoreCase("image/jpeg") || ct.equalsIgnoreCase("image/png"))) {
            throw new IOException("Only JPG/PNG allowed");
        }

        // Ensure uploads dir exists
        Path dir = Paths.get("uploads");
        if (!Files.exists(dir)) Files.createDirectories(dir);

        // Sanitize and uniquify filename
        String original = Paths.get(image.getOriginalFilename()).getFileName().toString();
        String ext = original.contains(".") ? original.substring(original.lastIndexOf('.')) : "";
        String unique = System.currentTimeMillis() + "_" + java.util.UUID.randomUUID() + ext;

        // Save
        Path target = dir.resolve(unique);
        Files.copy(image.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        // Return the PUBLIC path your UI can use
        return "/uploads/" + unique;
    }


    @Override
    public List<SliderResponseDto> getAllSliders() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public SliderResponseDto getSliderById(Long id) {
        Slider entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slider not found with id: " + id));
        return convertToDto(entity);
    }

    private SliderResponseDto convertToDto(Slider entity) {
        SliderResponseDto dto = new SliderResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setSubTitle(entity.getSubTitle());
        dto.setImages(entity.getImages());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setCreatedIp(entity.getCreatedIp());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setModifiedIp(entity.getModifiedIp());
        dto.setModifiedAt(entity.getModifiedAt());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public SliderResponseDto updateSliderFull(Long id, SliderRequestDto dto) {
        Slider entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slider not found with id: " + id));

        // Full update → overwrite all fields
        entity.setTitle(dto.getTitle());
        entity.setSubTitle(dto.getSubTitle());
        entity.setImages(dto.getImages());

        entity.setModifiedOn(LocalDateTime.now());
        entity.setModifiedIp("127.0.0.1");
        entity.setModifiedAt("system");

        return convertToDto(repository.save(entity));
    }

    @Override
    public SliderResponseDto updateSliderPartial(Long id, SliderRequestDto dto) {
        Slider entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slider not found with id: " + id));

        // Partial update → update only non-null fields
        if (dto.getTitle() != null) entity.setTitle(dto.getTitle());
        if (dto.getSubTitle() != null) entity.setSubTitle(dto.getSubTitle());
        if (dto.getImages() != null) entity.setImages(dto.getImages());

        entity.setModifiedOn(LocalDateTime.now());
        entity.setModifiedIp("127.0.0.1");
        entity.setModifiedAt("system");

        return convertToDto(repository.save(entity));
    }


    @Override
    public void softDeleteSlider(Long id) {
        Slider entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slider not found with id: " + id));

        entity.setStatus("INACTIVE");
        entity.setModifiedOn(LocalDateTime.now());
        entity.setModifiedIp("127.0.0.1");
        entity.setModifiedAt("system");

        repository.save(entity);
    }

}

