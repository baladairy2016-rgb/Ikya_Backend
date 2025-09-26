package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Dto.BlogRequestDto;
import com.vidhuras.hospital_app.Dto.BlogResponseDto;
import com.vidhuras.hospital_app.Dto.FacilitiesRequestDto;
import com.vidhuras.hospital_app.Dto.FacilitiesResponseDto;
import com.vidhuras.hospital_app.Service.BlogService;
import com.vidhuras.hospital_app.Service.FacilitiesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


    @RestController
    @RequestMapping("/Blogs")
    @CrossOrigin(origins = "*")
    public class BlogController {

        private final BlogService service;

        public BlogController(BlogService service) {
            this.service = service;
        }


        @PostMapping("/add")
        public ResponseEntity<BlogResponseDto> addBlog(
                @RequestParam("title") String title,
                @RequestParam("description") String description,
                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                @RequestParam(value = "iconFile", required = false) MultipartFile iconFile,
                @RequestParam(value = "createdBy", defaultValue = "Admin") String createdBy) throws IOException {

            BlogRequestDto dto = new BlogRequestDto();
            dto.setDescription(description);
            dto.setTitle(title);
            return ResponseEntity.ok(service.addBlog(dto, createdBy, imageFile, iconFile));
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<BlogResponseDto> updateBlog(
                @PathVariable Long id,
                @RequestParam(value = "description", required = false) String description,
                @RequestParam(value = "title", required = false) String title,
                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                @RequestParam(value = "iconFile", required = false) MultipartFile iconFile,
                @RequestParam(value = "modifiedBy", defaultValue = "Admin") String modifiedBy
        ) throws IOException {

            BlogRequestDto dto = new BlogRequestDto();
            dto.setTitle(title);
            dto.setDescription(description);
            BlogResponseDto updated = service.updateBlog(id, dto, modifiedBy, imageFile, iconFile);
            return ResponseEntity.ok(updated);
        }


        // ✅ Get all facilities
        @GetMapping("/all")
        public ResponseEntity<List<BlogResponseDto>> getAllBlogs() {
            return ResponseEntity.ok(service.getAllBlogs());
        }

        // ✅ Get facility by ID
        @GetMapping("/{id}")
        public ResponseEntity<BlogResponseDto> getFacilityById(@PathVariable Long id) {
            return ResponseEntity.ok(service.getBlogById(id));
        }

        // ✅ Delete facility
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
            service.deleteBlog(id);
            return ResponseEntity.ok("Facility deleted successfully");
        }
    }

