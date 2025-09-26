package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Dto.IkyaServiceResponseDto;
import com.vidhuras.hospital_app.Entity.Services;
import com.vidhuras.hospital_app.Repository.IkyaServiceRepository;
import com.vidhuras.hospital_app.Service.IkyaServices;
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
public class IkyaServiceImpl implements IkyaServices {

    private final IkyaServiceRepository repository;
    private final String uploadDir = "uploads/";

    public IkyaServiceImpl(IkyaServiceRepository repository) {
        this.repository = repository;
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
    }

    @Override
    public IkyaServiceResponseDto addDoctors(String serviceName, String description, int popularService, MultipartFile imageFile) {
        try {
            String imageUrl = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(imageFile.getInputStream(), Paths.get(filePath));
                imageUrl = "/uploads/" + fileName;
            }

            Services slot = new Services();
            slot.setServiceName(serviceName);
            slot.setDescription(description);
            slot.setImage(imageUrl);
            slot.setPopularService(popularService); // ✅ set popular service
            slot.setCreatedOn(LocalDateTime.now());

            Services saved = repository.save(slot);
            return mapToDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error saving service", e);
        }
    }

    @Override
    public List<IkyaServiceResponseDto> getAllDoctors() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public IkyaServiceResponseDto updateDoctor(Long id, String serviceName, String description, int popularService, MultipartFile imageFile) {
        try {
            Services slot = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Service not found with id " + id));

            slot.setServiceName(serviceName);
            slot.setDescription(description);
            slot.setPopularService(popularService); // ✅ update popular service

            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(imageFile.getInputStream(), Paths.get(filePath));
                slot.setImage("/uploads/" + fileName);
            }

            Services updated = repository.save(slot);
            return mapToDto(updated);

        } catch (IOException e) {
            throw new RuntimeException("Error updating service", e);
        }
    }

    @Override
    public void deleteDoctor(Long id) {
        Services slot = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id " + id));

        if (slot.getImage() != null) {
            String filePath = slot.getImage().replace("/uploads/", uploadDir);
            try {
                Files.deleteIfExists(Paths.get(filePath));
            } catch (IOException e) {
                System.out.println("Could not delete file: " + filePath);
            }
        }

        repository.delete(slot);
    }

    private IkyaServiceResponseDto mapToDto(Services slot) {
        IkyaServiceResponseDto dto = new IkyaServiceResponseDto();
        dto.setId(slot.getId());
        dto.setServiceName(slot.getServiceName());
        dto.setDescription(slot.getDescription());
        dto.setImage(slot.getImage());
        dto.setPopularService(slot.getPopularService()); // ✅ include popular service in DTO

        dto.setCreatedOn(slot.getCreatedOn());
        dto.setCreatedBy(slot.getCreatedBy());
        dto.setCreatedIp(slot.getCreatedIp());
        dto.setModifiedOn(slot.getModifiedOn());
        dto.setModifiedBy(slot.getModifiedBy());
        dto.setModifiedIp(slot.getModifiedIp());
        dto.setStatus(slot.getStatus());

        return dto;
    }
}