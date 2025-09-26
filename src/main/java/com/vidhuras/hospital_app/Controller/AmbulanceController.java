package com.vidhuras.hospital_app.Controller;


import com.vidhuras.hospital_app.Dto.AmbulanceRequestDto;
import com.vidhuras.hospital_app.Dto.AmbulanceResponseDto;
import com.vidhuras.hospital_app.Dto.AppointmentRequestDto;
import com.vidhuras.hospital_app.Dto.AppointmentResponseDto;
import com.vidhuras.hospital_app.Service.AmbulanceService;
import com.vidhuras.hospital_app.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AmbulanceService")
@CrossOrigin("*")
public class AmbulanceController {
    @Autowired
    private AmbulanceService service;

    @PostMapping
    public ResponseEntity<AmbulanceResponseDto> createService(
            @RequestBody AmbulanceRequestDto dto) {
        return ResponseEntity.ok(service.createService(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AmbulanceResponseDto> updateAmbulance(
            @PathVariable Long id, @RequestBody AmbulanceRequestDto dto) {
        return ResponseEntity.ok(service.updateAmbulance(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmbulanceResponseDto> getAmbulance(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAmbulanceById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AmbulanceResponseDto>> getAllAmbulances() {
        return ResponseEntity.ok(service.getAllAmbulances());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmbulance(@PathVariable Long id) {
        service.deleteAmbulance(id);
        return ResponseEntity.noContent().build();
    }
}
