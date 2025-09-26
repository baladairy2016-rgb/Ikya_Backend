package com.vidhuras.hospital_app.Controller;



import com.vidhuras.hospital_app.Dto.DoctorSetupRequestDto;
import com.vidhuras.hospital_app.Dto.DoctorSetupResponseDto;
import com.vidhuras.hospital_app.Entity.Appointment;
import com.vidhuras.hospital_app.Entity.Doctor;
import com.vidhuras.hospital_app.Service.DoctorSetupService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)

public class DoctorSetupController {

    private final DoctorSetupService service;

    public DoctorSetupController(DoctorSetupService service) {
        this.service = service;
    }
//
//    @PostMapping(value = "/add", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<DoctorSetupResponseDto> addDoctor(
//            @RequestPart(value = "doctor", required = false) DoctorSetupRequestDto doctorDto,
//            @RequestParam("image") MultipartFile imageFile,
//            @RequestBody(required = false) DoctorSetupRequestDto doctorJson) {
//
//        DoctorSetupRequestDto dto = doctorDto != null ? doctorDto : doctorJson;
//
//        DoctorSetupResponseDto response = service.addDoctor(dto, imageFile);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping(
            value = "/add",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<DoctorSetupResponseDto> addDoctor(
            @RequestPart("doctor") DoctorSetupRequestDto doctorDto,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {

        DoctorSetupResponseDto response = service.addDoctor(doctorDto, imageFile);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<List<DoctorSetupResponseDto>> getAllDoctors() {
        return ResponseEntity.ok(service.getAllDoctors());
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorId) {
        return ResponseEntity.ok(service.getDoctorById(doctorId));
    }


//    @PutMapping(value = "/update/{doctorId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<DoctorSetupResponseDto> updateDoctor(
//            @PathVariable Long doctorId,
//            @RequestPart(value = "doctor", required = false) DoctorSetupRequestDto doctorDto,
//            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
//            @RequestBody(required = false) DoctorSetupRequestDto doctorJson) {
//
//        DoctorSetupRequestDto dto = doctorDto != null ? doctorDto : doctorJson;
//        DoctorSetupResponseDto response = service.updateDoctor(doctorId, dto, imageFile);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping(value = "/update/{doctorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DoctorSetupResponseDto> updateDoctor(
            @PathVariable Long doctorId,
            @RequestPart("doctor") DoctorSetupRequestDto doctorDto,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {

        DoctorSetupResponseDto response = service.updateDoctor(doctorId, doctorDto, imageFile);
        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId) {
        service.deleteDoctor(doctorId);
        return ResponseEntity.ok("Doctor deleted successfully!");
    }
}

