package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Dto.ConsultationRequestDto;
import com.vidhuras.hospital_app.Dto.ConsultationResponseDto;
import com.vidhuras.hospital_app.Entity.Doctor;
import com.vidhuras.hospital_app.Service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/consultations")
    @CrossOrigin(origins = "*")
    public class ConsultationController {

        private final ConsultationService service;

        public ConsultationController(ConsultationService service) {
            this.service = service;
        }

        @PostMapping("/add")
        public ResponseEntity<ConsultationResponseDto> addConsultation(@RequestBody ConsultationRequestDto dto) {
            return ResponseEntity.ok(service.addConsultation(dto));
        }

        @GetMapping("/all")
        public ResponseEntity<List<ConsultationResponseDto>> getAllConsultations() {
            return ResponseEntity.ok(service.getAllConsultations());
        }


        @GetMapping("/{consultationId}")
        public ResponseEntity<ConsultationResponseDto> getConsultationById(@PathVariable Long consultationId) {
            return ResponseEntity.ok(service.getConsultationById(consultationId));
        }

        @PutMapping("/update/{consultationId}")
        public ResponseEntity<ConsultationResponseDto> updateConsultation(
                @PathVariable Long consultationId,
                @RequestBody ConsultationRequestDto dto) {
            return ResponseEntity.ok(service.updateConsultation(consultationId, dto));
        }

        @DeleteMapping("/delete/{consultationId}")
        public ResponseEntity<String> deleteConsultation(@PathVariable Long consultationId) {
            service.deleteConsultation(consultationId);
            return ResponseEntity.ok("Consultation marked as INACTIVE!");
        }
}
