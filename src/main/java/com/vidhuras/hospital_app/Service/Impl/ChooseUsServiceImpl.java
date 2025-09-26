package com.vidhuras.hospital_app.Service.Impl;


import com.vidhuras.hospital_app.Dto.ChooseUsResponseDto;
import com.vidhuras.hospital_app.Entity.ChooseUs;
import com.vidhuras.hospital_app.Repository.ChooseUsRepository;
import com.vidhuras.hospital_app.Service.ChooseUsService;
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
public class ChooseUsServiceImpl implements ChooseUsService {

    private final ChooseUsRepository repository;
    private final String uploadDir = "uploads/"; // Folder to store images

    public ChooseUsServiceImpl(ChooseUsRepository repository) {
        this.repository = repository;
        // create upload folder if not exists
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
    }

    @Override
    public ChooseUsResponseDto createService(String serviceName, String description,  MultipartFile iconFile) throws IOException {
        try {


            ChooseUs slot = new ChooseUs();
            slot.setServiceName(serviceName);
            slot.setDescription(description);
            slot.setCreatedOn(LocalDateTime.now());

            if (iconFile != null && !iconFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + iconFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(iconFile.getInputStream(), Paths.get(filePath));
                slot.setIcon("/uploads/" + fileName);
            }
            ChooseUs saved = repository.save(slot);
            return mapToDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error saving service", e);
        }
    }

    @Override
    public List<ChooseUsResponseDto> getAllServices() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChooseUsResponseDto updateSlot(Long id, String serviceName, String description, MultipartFile iconFile) throws IOException {
        try {
            ChooseUs slot = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Service not found with id " + id));

            slot.setServiceName(serviceName);
            slot.setDescription(description);


            // ✅ Update icon only if new file uploaded
            if (iconFile != null && !iconFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + iconFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(iconFile.getInputStream(), Paths.get(filePath));
                slot.setIcon("/uploads/" + fileName);
            }
            ChooseUs updated = repository.save(slot);
            return mapToDto(updated);

        } catch (IOException e) {
            throw new RuntimeException("Error updating service", e);
        }
    }

    @Override
    public void deleteSlot(Long id) {
        ChooseUs slot = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id " + id));



        repository.delete(slot);
    }

    // helper
    private ChooseUsResponseDto mapToDto(ChooseUs slot) {
        ChooseUsResponseDto dto = new ChooseUsResponseDto();
        dto.setId(slot.getId());
        dto.setServiceName(slot.getServiceName());
        dto.setDescription(slot.getDescription());
        dto.setIcon(slot.getIcon());
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