package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Dto.SpecialitiesRequestDto;
import com.vidhuras.hospital_app.Dto.SpecialitiesResponseDto;
import com.vidhuras.hospital_app.Service.SpecialitiesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/Specialities")
@CrossOrigin(origins = "*")
public class SpecialitiesController {

    private final SpecialitiesService service;

    public SpecialitiesController(SpecialitiesService service) {
        this.service = service;
    }

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<SpecialitiesResponseDto> addFacility(
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "iconFile", required = false) MultipartFile iconFile,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "image1", required = false) MultipartFile image1
    ) throws IOException {
        SpecialitiesRequestDto dto = new SpecialitiesRequestDto();
        dto.setTitle(title);
        dto.setDescription(description);
        return ResponseEntity.ok(service.addFacility(dto, "Admin", iconFile, image, image1));
    }

    @PutMapping(value = "/update/{id}", consumes = "multipart/form-data")
    public ResponseEntity<SpecialitiesResponseDto> updateFacility(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "iconFile", required = false) MultipartFile iconFile,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "image1", required = false) MultipartFile image1
    ) throws IOException {
        SpecialitiesRequestDto dto = new SpecialitiesRequestDto();
        dto.setTitle(title);
        dto.setDescription(description);
        return ResponseEntity.ok(service.updateFacility(id, dto, "Admin", iconFile, image, image1));
    }


    @GetMapping("/all")
    public ResponseEntity<List<SpecialitiesResponseDto>> getAllFacilities() {
        return ResponseEntity.ok(service.getAllFacilities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialitiesResponseDto> getFacilityById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFacilityById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        service.deleteFacility(id);
        return ResponseEntity.ok().build();
    }
}