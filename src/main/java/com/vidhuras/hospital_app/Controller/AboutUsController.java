package com.vidhuras.hospital_app.Controller;


import com.vidhuras.hospital_app.Dto.AboutResponseDto;
import com.vidhuras.hospital_app.Service.AboutService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/About")
@CrossOrigin(origins = "*")
public class AboutUsController {

    private final AboutService service;

    public AboutUsController(AboutService service) {

        this.service = service;
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AboutResponseDto> createService(
            @RequestParam String title,
            @RequestParam String subTitle,
            @RequestParam String description,
            @RequestParam MultipartFile imageFile) {

        AboutResponseDto response = service.addAbout(title, subTitle,description, imageFile);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AboutResponseDto> updateUs(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String subTitle,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile imageFile) {
        AboutResponseDto response = service.updateUs(id, title,subTitle, description, imageFile);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AboutResponseDto>> getAllServices() {
        List<AboutResponseDto> services = service.getAllServices();
        return ResponseEntity.ok(services);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSlot(@PathVariable Long id) {
        service.deleteSlot(id);
        return ResponseEntity.ok("Service deleted successfully!");
    }
}

