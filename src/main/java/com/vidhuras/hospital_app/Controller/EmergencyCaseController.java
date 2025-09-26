package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Dto.EmergencyCaseRequestDto;
import com.vidhuras.hospital_app.Dto.EmergencyCaseResponseDto;
import com.vidhuras.hospital_app.Service.EmergencyCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency")
@CrossOrigin(origins = "*")
public class EmergencyCaseController {

    @Autowired
    private EmergencyCaseService emergencyCaseService;

    // Create
    @PostMapping("/add")
    public ResponseEntity<EmergencyCaseResponseDto> addEmergencyCase(@RequestBody EmergencyCaseRequestDto dto) {
        return ResponseEntity.ok(emergencyCaseService.createEmergencyCase(dto));
    }

    // Get all
    @GetMapping("/all")
    public ResponseEntity<List<EmergencyCaseResponseDto>> getAllEmergencyCases() {
        return ResponseEntity.ok(emergencyCaseService.getAllEmergencyCases());
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmergencyCaseResponseDto> getEmergencyCaseById(@PathVariable Long id) {
        return ResponseEntity.ok(emergencyCaseService.getEmergencyCaseById(id));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<EmergencyCaseResponseDto> updateEmergencyCase(
            @PathVariable Long id,
            @RequestBody EmergencyCaseRequestDto dto) {
        return ResponseEntity.ok(emergencyCaseService.updateEmergencyCase(id, dto));
    }

    // Delete (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmergencyCase(@PathVariable Long id) {
        emergencyCaseService.deleteEmergencyCase(id);
        return ResponseEntity.ok("Emergency case status updated to CANCELLED for id " + id);
    }
}


