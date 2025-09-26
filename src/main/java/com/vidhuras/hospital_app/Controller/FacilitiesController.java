package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Dto.FacilitiesRequestDto;
import com.vidhuras.hospital_app.Dto.FacilitiesResponseDto;
import com.vidhuras.hospital_app.Service.FacilitiesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/facilities")
@CrossOrigin(origins = "*")
public class FacilitiesController {

    private final FacilitiesService facilitiesService;

    public FacilitiesController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }


    @PostMapping("/add")
    public ResponseEntity<FacilitiesResponseDto> addFacility(
            @RequestParam("title") String title,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "iconFile", required = false) MultipartFile iconFile,
            @RequestParam(value = "createdBy", defaultValue = "Admin") String createdBy) throws IOException {

        FacilitiesRequestDto dto = new FacilitiesRequestDto();
        dto.setTitle(title);
        return ResponseEntity.ok(facilitiesService.addFacility(dto, createdBy, imageFile, iconFile));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FacilitiesResponseDto> updateFacility(
            @PathVariable Long id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "iconFile", required = false) MultipartFile iconFile,
            @RequestParam(value = "modifiedBy", defaultValue = "Admin") String modifiedBy
    ) throws IOException {

        FacilitiesRequestDto dto = new FacilitiesRequestDto();
        dto.setTitle(title);

        FacilitiesResponseDto updated = facilitiesService.updateFacility(id, dto, modifiedBy, imageFile, iconFile);
        return ResponseEntity.ok(updated);
    }


    // ✅ Update facility
//    @PutMapping("/update/{id}")
//    public ResponseEntity<FacilitiesResponseDto> updateFacility(
//            @PathVariable Long id,
//            @RequestParam("title") String title,
//            @RequestParam(value = "status", defaultValue = "true") boolean status,
//            @RequestParam(value = "image", required = false) MultipartFile imageFile,
//            @RequestParam(value = "icon", required = false) MultipartFile iconFile,
//            @RequestParam(value = "modifiedBy", defaultValue = "Admin") String modifiedBy
//    ) throws IOException {
//        FacilitiesRequestDto dto = new FacilitiesRequestDto();
//        dto.setTitle(title);
//
//        if (iconFile != null && !iconFile.isEmpty()) {
//            dto.setIcon("/uploads/" + System.currentTimeMillis() + "_" + iconFile.getOriginalFilename());
//        }
//
//        FacilitiesResponseDto updated = facilitiesService.updateFacility(id, dto, modifiedBy, imageFile) ;
//        return ResponseEntity.ok(updated);
//    }

    // ✅ Get all facilities
    @GetMapping("/all")
    public ResponseEntity<List<FacilitiesResponseDto>> getAllFacilities() {
        return ResponseEntity.ok(facilitiesService.getAllFacilities());
    }

    // ✅ Get facility by ID
    @GetMapping("/{id}")
    public ResponseEntity<FacilitiesResponseDto> getFacilityById(@PathVariable Long id) {
        return ResponseEntity.ok(facilitiesService.getFacilityById(id));
    }

    // ✅ Delete facility
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFacility(@PathVariable Long id) {
        facilitiesService.deleteFacility(id);
        return ResponseEntity.ok("Facility deleted successfully");
    }
}
