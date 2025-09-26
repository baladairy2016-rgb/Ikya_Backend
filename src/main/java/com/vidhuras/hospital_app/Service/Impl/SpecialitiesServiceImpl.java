package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Dto.SpecialitiesRequestDto;
import com.vidhuras.hospital_app.Dto.SpecialitiesResponseDto;
import com.vidhuras.hospital_app.Entity.Specialities;
import com.vidhuras.hospital_app.Repository.SpecialitiesRepository;
import com.vidhuras.hospital_app.Service.SpecialitiesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialitiesServiceImpl implements SpecialitiesService {

    private final SpecialitiesRepository repository;
    private final String uploadDir = "uploads/";

    public SpecialitiesServiceImpl(SpecialitiesRepository repository) {
        this.repository = repository;
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
    }

    @Override
    public SpecialitiesResponseDto addFacility(SpecialitiesRequestDto dto, String createdBy,
                                               MultipartFile iconFile, MultipartFile image, MultipartFile image1) throws IOException {
        Specialities facility = new Specialities();
        facility.setTitle(dto.getTitle());
        facility.setDescription(dto.getDescription());
        facility.setStatus("ACTIVE");
        facility.setCreatedOn(LocalDateTime.now());
        facility.setCreatedBy(createdBy);

        if (iconFile != null && !iconFile.isEmpty()) {
            String fileName = saveFile(iconFile);
            facility.setIcon("/uploads/" + fileName);
        }
        if (image != null && !image.isEmpty()) {
            String fileName = saveFile(image);
            facility.setImage("/uploads/" + fileName);
        }
        if (image1 != null && !image1.isEmpty()) {
            String fileName = saveFile(image1);
            facility.setImage1("/uploads/" + fileName);
        }

        repository.save(facility);
        return mapToDto(facility);
    }

    @Override
    public SpecialitiesResponseDto updateFacility(Long id, SpecialitiesRequestDto dto, String modifiedBy,
                                                  MultipartFile iconFile, MultipartFile image, MultipartFile image1) throws IOException {
        Specialities facility = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with ID " + id));

        if (dto.getTitle() != null && !dto.getTitle().isBlank()) facility.setTitle(dto.getTitle());
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) facility.setDescription(dto.getDescription());

        if (iconFile != null && !iconFile.isEmpty()) {
            String fileName = saveFile(iconFile);
            facility.setIcon("/uploads/" + fileName);
        }
        if (image != null && !image.isEmpty()) {
            String fileName = saveFile(image);
            facility.setImage("/uploads/" + fileName);
        }
        if (image1 != null && !image1.isEmpty()) {
            String fileName = saveFile(image1);
            facility.setImage1("/uploads/" + fileName);
        }

        facility.setModifiedOn(LocalDateTime.now());
        facility.setModifiedBy(modifiedBy);

        repository.save(facility);
        return mapToDto(facility);
    }

    @Override
    public SpecialitiesResponseDto getFacilityById(Long id) {
        Specialities facility = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with ID " + id));
        return mapToDto(facility);
    }

    @Override
    public void deleteFacility(Long id) {
        Specialities facility = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with ID " + id));
        facility.setStatus("INACTIVE");
        facility.setModifiedOn(LocalDateTime.now());
        facility.setModifiedBy("Admin");
        repository.save(facility);
    }

    @Override
    public List<SpecialitiesResponseDto> getAllFacilities() {
        return repository.findByStatus("ACTIVE")
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private String saveFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + fileName;
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }

    private SpecialitiesResponseDto mapToDto(Specialities facility) {
        SpecialitiesResponseDto dto = new SpecialitiesResponseDto();
        dto.setId(facility.getId());
        dto.setTitle(facility.getTitle());
        dto.setDescription(facility.getDescription());
        dto.setIcon(facility.getIcon());
        dto.setImage(facility.getImage());
        dto.setImage1(facility.getImage1());
        dto.setCreatedOn(facility.getCreatedOn());
        dto.setCreatedBy(facility.getCreatedBy());
        dto.setModifiedOn(facility.getModifiedOn());
        dto.setModifiedBy(facility.getModifiedBy());
        return dto;
    }
}