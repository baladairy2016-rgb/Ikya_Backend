package com.vidhuras.hospital_app.Service.Impl;


import com.vidhuras.hospital_app.Dto.FacilitiesRequestDto;
import com.vidhuras.hospital_app.Dto.FacilitiesResponseDto;
import com.vidhuras.hospital_app.Entity.Facilities;
import com.vidhuras.hospital_app.Repository.FacilitiesRepository;
import com.vidhuras.hospital_app.Service.FacilitiesService;
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
    public class FacilitiesServiceImpl implements FacilitiesService {

        private final FacilitiesRepository repository;
    private final String uploadDir = "uploads/";

    public FacilitiesServiceImpl(FacilitiesRepository repository) {
        this.repository = repository;
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
    }


        @Override
        public FacilitiesResponseDto addFacility(FacilitiesRequestDto dto, String createdBy, MultipartFile imageFile, MultipartFile iconFile) throws IOException {
            Facilities facility = new Facilities();
            facility.setTitle(dto.getTitle());
            facility.setStatus("ACTIVE");
            facility.setCreatedOn(LocalDateTime.now());
            facility.setCreatedBy(createdBy);

            String imageUrl = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(imageFile.getInputStream(), Paths.get(filePath));
                imageUrl = "/uploads/" + fileName; // public URL
            }

            if (iconFile != null && !iconFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + iconFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(iconFile.getInputStream(), Paths.get(filePath));
                facility.setIcon("/uploads/" + fileName);
            }
            facility.setImage(imageUrl);
            repository.save(facility);
            return mapToDto(facility);
        }

    @Override
    public FacilitiesResponseDto updateFacility(Long id,
                                                FacilitiesRequestDto dto,
                                                String modifiedBy,
                                                MultipartFile imageFile,
                                                MultipartFile iconFile) throws IOException {
        Facilities facility = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with ID " + id));

        // ✅ Update title only if provided
        if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
            facility.setTitle(dto.getTitle());
        }

        // ✅ Update image only if new file uploaded
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String filePath = uploadDir + fileName;
            Files.copy(imageFile.getInputStream(), Paths.get(filePath));
            facility.setImage("/uploads/" + fileName);
        }

        // ✅ Update icon only if new file uploaded
        if (iconFile != null && !iconFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + iconFile.getOriginalFilename();
            String filePath = uploadDir + fileName;
            Files.copy(iconFile.getInputStream(), Paths.get(filePath));
            facility.setIcon("/uploads/" + fileName);
        }

        facility.setModifiedOn(LocalDateTime.now());
        facility.setModifiedBy(modifiedBy);

        repository.save(facility);
        return mapToDto(facility);
    }



    @Override
        public FacilitiesResponseDto getFacilityById(Long id) {
            Facilities facility = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Facility not found with ID " + id));
            return mapToDto(facility);
        }



    @Override
    public void deleteFacility(Long id) {
        Facilities facility = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with ID " + id));

        facility.setStatus("INACTIVE"); // soft delete
        facility.setModifiedOn(LocalDateTime.now());
        facility.setModifiedBy("Admin"); // or pass current user
        repository.save(facility);
    }



    @Override
    public List<FacilitiesResponseDto> getAllFacilities() {
        return repository.findByStatus("ACTIVE").stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    private FacilitiesResponseDto mapToDto(Facilities facility) {
            FacilitiesResponseDto dto = new FacilitiesResponseDto();
            dto.setId(facility.getId());
            dto.setTitle(facility.getTitle());
            dto.setImage(facility.getImage());
            dto.setIcon(facility.getIcon());
            dto.setCreatedOn(facility.getCreatedOn());
            dto.setCreatedBy(facility.getCreatedBy());
            dto.setModifiedOn(facility.getModifiedOn());
            dto.setModifiedBy(facility.getModifiedBy());
            return dto;
        }
    }

