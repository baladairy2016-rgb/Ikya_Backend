package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.DoctorSetupRequestDto;
import com.vidhuras.hospital_app.Dto.DoctorSetupResponseDto;
import com.vidhuras.hospital_app.Entity.Doctor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DoctorSetupService {

    List<DoctorSetupResponseDto> getAllDoctors();


    void deleteDoctor(Long doctorId);

    DoctorSetupResponseDto addDoctor(DoctorSetupRequestDto doctorDto, MultipartFile imageFile);

    DoctorSetupResponseDto updateDoctor(Long doctorId, DoctorSetupRequestDto doctorDto, MultipartFile imageFile);

    Doctor getDoctorById(Long doctorId);
}
