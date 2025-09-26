package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Dto.DoctorSetupRequestDto;
import com.vidhuras.hospital_app.Dto.DoctorSetupResponseDto;
import com.vidhuras.hospital_app.Dto.PatientRequestDto;
import com.vidhuras.hospital_app.Dto.PatientResponseDto;
import com.vidhuras.hospital_app.Service.DoctorSetupService;
import com.vidhuras.hospital_app.Service.PatientService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @PostMapping(value = "/add", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PatientResponseDto> createPatient(
            @RequestPart(value = "patient", required = false) PatientRequestDto patientDto,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestBody(required = false) PatientRequestDto patientJson) {

        PatientRequestDto dto = patientDto != null ? patientDto : patientJson;
        PatientResponseDto response = service.createPatient(dto, imageFile);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        return ResponseEntity.ok(service.getAllPatients());
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long patientId) {
        PatientResponseDto response = service.getPatientById(patientId);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{patientId}")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable Long patientId,
            @RequestBody PatientRequestDto patientDto) {

        PatientResponseDto response = service.updatePatient(patientId, patientDto, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable Long patientId) {
        service.deletePatient(patientId);
        return ResponseEntity.ok("Patient deleted successfully!");
    }
}
