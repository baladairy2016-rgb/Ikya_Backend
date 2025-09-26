package com.vidhuras.hospital_app.Controller;


import com.vidhuras.hospital_app.Dto.ChooseUsResponseDto;
import com.vidhuras.hospital_app.Service.ChooseUsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/WhyChooseUs")
@CrossOrigin(origins = "*")
public class ChooseUsController {

    private final ChooseUsService service;

    public ChooseUsController(ChooseUsService service) {

        this.service = service;
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ChooseUsResponseDto> createService(
            @RequestParam String serviceName,
            @RequestParam String description,
            @RequestParam(value = "iconFile", required = false) MultipartFile iconFile) throws IOException {

        ChooseUsResponseDto response = service.createService(serviceName, description,iconFile);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChooseUsResponseDto>> getAllServices() {
        List<ChooseUsResponseDto> services = service.getAllServices();
        return ResponseEntity.ok(services);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ChooseUsResponseDto> updateSlot(
            @PathVariable Long id,
            @RequestParam String serviceName,
            @RequestParam String description,
            @RequestParam(value = "iconFile", required = false) MultipartFile iconFile) throws IOException {
        ChooseUsResponseDto response = service.updateSlot(id, serviceName, description,iconFile);
        return ResponseEntity.ok(response);
    }

    // ✅ Delete Service
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSlot(@PathVariable Long id) {
        service.deleteSlot(id);
        return ResponseEntity.ok("Service deleted successfully!");
    }
}
