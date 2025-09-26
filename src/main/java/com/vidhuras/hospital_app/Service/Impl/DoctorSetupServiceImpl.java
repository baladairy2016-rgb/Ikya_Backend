package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Dto.DoctorSetupRequestDto;
import com.vidhuras.hospital_app.Dto.DoctorSetupResponseDto;
import com.vidhuras.hospital_app.Entity.Appointment;
import com.vidhuras.hospital_app.Entity.Doctor;
import com.vidhuras.hospital_app.Repository.DoctorSetupRepository;
import com.vidhuras.hospital_app.Service.DoctorSetupService;
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
public class DoctorSetupServiceImpl implements DoctorSetupService {

    private final DoctorSetupRepository repository;
    private final String uploadDir = "uploads/"; // Folder to store images

    public DoctorSetupServiceImpl(DoctorSetupRepository repository) {
        this.repository = repository;
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
    }

    @Override
    public DoctorSetupResponseDto addDoctor(DoctorSetupRequestDto dto, MultipartFile imageFile) {
        try {
            String imageUrl = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(imageFile.getInputStream(), Paths.get(filePath));
                imageUrl = "/uploads/" + fileName;
            }

            Doctor doctor = new Doctor();
            doctor.setDoctorName(dto.getDoctorName());
            doctor.setDescription(dto.getDescription());
            doctor.setEmail(dto.getEmail());
            doctor.setPhone(dto.getPhone());
            doctor.setSpecialization(dto.getSpecialization());
            doctor.setExperience(dto.getExperience());
            doctor.setQualification(dto.getQualification());
            doctor.setGender(dto.getGender());
            doctor.setConsultingFee(dto.getConsultingFee());
            doctor.setAvailableDays(dto.getAvailableDays());
            doctor.setAvailableTime(dto.getAvailableTime());
            doctor.setProfileImage(imageUrl);
            doctor.setCreatedOn(LocalDateTime.now());
            doctor.setCreatedBy(dto.getCreatedBy());
            doctor.setCreatedIp(dto.getCreatedIp());
            doctor.setStatus("ACTIVE");

            Doctor saved = repository.save(doctor);
            return mapToDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error saving doctor", e);
        }
    }


    @Override
    public List<DoctorSetupResponseDto> getAllDoctors() {
        return repository.findByStatusIgnoreCase("ACTIVE")
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public Doctor getDoctorById(Long doctorId) {
        return repository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with doctorId " + doctorId));
    }

    @Override
    public DoctorSetupResponseDto updateDoctor(Long doctorId, DoctorSetupRequestDto dto, MultipartFile imageFile) {
        try {
            Doctor doctor = repository.findById(doctorId)
                    .orElseThrow(() -> new RuntimeException("Doctor not found with doctorId " + doctorId));

            doctor.setDoctorName(dto.getDoctorName());
            doctor.setDescription(dto.getDescription());
            doctor.setEmail(dto.getEmail());
            doctor.setPhone(dto.getPhone());
            doctor.setSpecialization(dto.getSpecialization());
            doctor.setExperience(dto.getExperience());
            doctor.setQualification(dto.getQualification());
            doctor.setGender(dto.getGender());
            doctor.setConsultingFee(dto.getConsultingFee());
            doctor.setAvailableDays(dto.getAvailableDays());
            doctor.setAvailableTime(dto.getAvailableTime());

            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String filePath = uploadDir + fileName;
                Files.copy(imageFile.getInputStream(), Paths.get(filePath));
                doctor.setProfileImage("/uploads/" + fileName);
            }

            doctor.setModifiedOn(LocalDateTime.now());
            doctor.setModifiedBy(dto.getModifiedBy());
            doctor.setModifiedIp(dto.getModifiedIp());

            Doctor updated = repository.save(doctor);
            return mapToDto(updated);

        } catch (IOException e) {
            throw new RuntimeException("Error updating doctor", e);
        }
    }


    @Override
    public void deleteDoctor(Long doctorId) {
        Doctor doctor = repository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with doctorId " + doctorId));

        doctor.setStatus("INACTIVE");
        doctor.setModifiedOn(LocalDateTime.now());
        repository.save(doctor);
    }


    private DoctorSetupResponseDto mapToDto(Doctor doctor) {
        DoctorSetupResponseDto dto = new DoctorSetupResponseDto();
        dto.setDoctorId(doctor.getDoctorId());
        dto.setDoctorName(doctor.getDoctorName());
        dto.setDescription(doctor.getDescription());
        dto.setEmail(doctor.getEmail());
        dto.setPhone(doctor.getPhone());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setExperience(doctor.getExperience());
        dto.setQualification(doctor.getQualification());
        dto.setGender(doctor.getGender());
        dto.setConsultingFee(doctor.getConsultingFee());
        dto.setAvailableDays(doctor.getAvailableDays());
        dto.setAvailableTime(doctor.getAvailableTime());
        dto.setProfileImage(doctor.getProfileImage());

        dto.setCreatedOn(doctor.getCreatedOn());
        dto.setCreatedBy(doctor.getCreatedBy());
        dto.setCreatedIp(doctor.getCreatedIp());
        dto.setModifiedOn(doctor.getModifiedOn());
        dto.setModifiedBy(doctor.getModifiedBy());
        dto.setModifiedIp(doctor.getModifiedIp());
        dto.setStatus(doctor.getStatus());

        return dto;
    }
}


