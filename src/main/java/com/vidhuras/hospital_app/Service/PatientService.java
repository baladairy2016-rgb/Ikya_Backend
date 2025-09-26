package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.DoctorSetupRequestDto;
import com.vidhuras.hospital_app.Dto.DoctorSetupResponseDto;
import com.vidhuras.hospital_app.Dto.PatientRequestDto;
import com.vidhuras.hospital_app.Dto.PatientResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PatientService {

    PatientResponseDto createPatient(PatientRequestDto dto, MultipartFile imageFile);

    List<PatientResponseDto> getAllPatients();

    PatientResponseDto getPatientById(Long patientId);



    void deletePatient(Long patientId);

    PatientResponseDto updatePatient(Long patientId, PatientRequestDto patientDto, Object o);
}
