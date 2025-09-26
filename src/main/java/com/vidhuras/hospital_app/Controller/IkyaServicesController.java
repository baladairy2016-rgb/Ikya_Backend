package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Dto.IkyaServiceResponseDto;
import com.vidhuras.hospital_app.Service.IkyaServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


    @RestController
    @RequestMapping("/Services")
    @CrossOrigin(origins = "*")
    public class IkyaServicesController {

        private final IkyaServices service;

        public IkyaServicesController(IkyaServices service) {

            this.service = service;
        }

        @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<IkyaServiceResponseDto> addDoctors(
                @RequestParam String serviceName,
                @RequestParam String description,
                @RequestParam int popularService,
                @RequestParam MultipartFile imageFile) {

            IkyaServiceResponseDto response = service.addDoctors(serviceName, description, popularService, imageFile);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/all")
        public ResponseEntity<List<IkyaServiceResponseDto>> getAllDoctors() {
            List<IkyaServiceResponseDto> services = service.getAllDoctors();
            return ResponseEntity.ok(services);
        }

        @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<IkyaServiceResponseDto> updateDoctor(
                @PathVariable Long id,
                @RequestParam String serviceName,
                @RequestParam String description,
                @RequestParam int popularService,
                @RequestParam(required = false) MultipartFile imageFile) {
            IkyaServiceResponseDto response = service.updateDoctor(id, serviceName, description,popularService, imageFile);
            return ResponseEntity.ok(response);
        }

        // ✅ Delete Service
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
            service.deleteDoctor(id);
            return ResponseEntity.ok("Service deleted successfully!");
        }
    }
