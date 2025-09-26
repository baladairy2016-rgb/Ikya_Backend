package com.vidhuras.hospital_app.Service.Impl;


import com.vidhuras.hospital_app.Dto.AboutResponseDto;
import com.vidhuras.hospital_app.Entity.About;
import com.vidhuras.hospital_app.Repository.AboutRepository;
import com.vidhuras.hospital_app.Service.AboutService;
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
public class AboutServiceImpl implements AboutService {

    private final AboutRepository repository;
    private final String uploadDir = "uploads/";

    public AboutServiceImpl(AboutRepository repository) {
        this.repository = repository;
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
    }

    @Override
    public AboutResponseDto addAbout(String title,String subTitle, String description, MultipartFile imageFile) {
        try {
            // ✅ Check if already one record exists
            if (repository.count() > 0) {
                throw new RuntimeException("Only one About record is allowed. Please update the existing record.");
            }

            String imageUrl = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(imageFile.getInputStream(), Paths.get(filePath));
                imageUrl = "/uploads/" + fileName; // public URL
            }

            About about = new About();
            about.setTitle(title);
            about.setSubTitle(subTitle);
            about.setDescription(description);
            about.setImage(imageUrl);
            about.setCreatedOn(LocalDateTime.now());

            About saved = repository.save(about);
            return mapToDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error saving service", e);
        }
    }


    @Override
    public List<AboutResponseDto> getAllServices() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AboutResponseDto updateUs(Long id, String title,String subTitle, String description, MultipartFile imageFile) {
        try {
            About slot = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Service not found with id " + id));

            slot.setTitle(title);
            slot.setSubTitle(subTitle);
            slot.setDescription(description);

            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(imageFile.getInputStream(), Paths.get(filePath));
                slot.setImage("/uploads/" + fileName);
            }

            About updated = repository.save(slot);
            return mapToDto(updated);

        } catch (IOException e) {
            throw new RuntimeException("Error updating service", e);
        }
    }

    @Override
    public void deleteSlot(Long id) {
        About slot = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id " + id));

        // delete image file if exists
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

    // helper
    private AboutResponseDto mapToDto(About slot) {
        AboutResponseDto dto = new AboutResponseDto();
        dto.setId(slot.getId());
        dto.setTitle(slot.getTitle());
        dto.setSubTitle(slot.getSubTitle());
        dto.setDescription(slot.getDescription());
        dto.setImage(slot.getImage());

        dto.setCreatedOn(slot.getCreatedOn());
        dto.setCreatedBy(slot.getCreatedBy());
        dto.setCreatedIp(slot.getCreatedIp());
        dto.setModifiedOn(slot.getModifiedOn());
        dto.setModifiedBy(slot.getModifiedBy());
        dto.setModifiedIp(slot.getModifiedIp());
        return dto;
    }
}

