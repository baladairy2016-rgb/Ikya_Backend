package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Dto.SliderRequestDto;
import com.vidhuras.hospital_app.Dto.SliderResponseDto;
import com.vidhuras.hospital_app.Entity.Slider;
import com.vidhuras.hospital_app.Service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


    @RestController
    @RequestMapping("/Slider")
    @CrossOrigin(origins = "*")
    public class SliderController {

        private final SliderService service;

        @Autowired
        public SliderController(SliderService service) {
            this.service = service;
        }

        @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<Slider> createSlider(
                @RequestParam("title") String title,
                @RequestParam("subTitle") String subTitle,
                @RequestParam("image") MultipartFile image) throws IOException {

            Slider saved = service.saveSlider(title, subTitle, image);
            return ResponseEntity.ok(saved);
        }

        @PostMapping(value = "/addSlider", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<SliderResponseDto> createSlider(
                @RequestParam("title") String title,
                @RequestParam(value = "subTitle", required = false) String subTitle,
                @RequestParam("image") MultipartFile image,
                @RequestParam(value = "status", required = false) String status
        ) throws IOException{

            // Always use absolute path under your project directory
            String uploadDir = System.getProperty("user.dir")
                    + File.separator + "uploads"
                    + File.separator + "sliders"
                    + File.separator;

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create unique filename
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File dest = new File(uploadDir + fileName);

            // Save uploaded file
            image.transferTo(dest);

            // Public path that frontend can load via http://localhost:8080/uploads/sliders/...
            String publicPath = "/uploads/sliders/" + fileName;

            // Build DTO for service layer
            SliderRequestDto dto = new SliderRequestDto();
            dto.setTitle(title);
            dto.setSubTitle(subTitle);
            dto.setImages(publicPath);
            dto.setStatus(status != null ? status : "ACTIVE");

            // Call service
            return ResponseEntity.ok(service.addSlider(dto));
        }


        @GetMapping("/allSliders")
        public List<SliderResponseDto> getAllSliders() {
            return service.getAllSliders();
        }

        @GetMapping("/allSliders/{id}")
        public SliderResponseDto getSliderById(@PathVariable Long id) {
            return service.getSliderById(id);
        }

        @PutMapping(value = "/updateSlider/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public SliderResponseDto updateSliderFull(
                @PathVariable Long id,
                @RequestParam("title") String title,
                @RequestParam("subTitle") String subTitle,
                @RequestParam(value = "image", required = false) MultipartFile image
        ) throws IOException {

            SliderRequestDto dto = new SliderRequestDto();
            dto.setTitle(title);
            dto.setSubTitle(subTitle);

            if (image != null) {
                // Save the new image
                String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "sliders" + File.separator;
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                File dest = new File(uploadDir + fileName);
                image.transferTo(dest);

                dto.setImages("/uploads/sliders/" + fileName);
            }

            return service.updateSliderFull(id, dto);
        }

        @PatchMapping(value = "/updateSlider/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public SliderResponseDto updateSliderPartial(
                @PathVariable Long id,
                @RequestParam(value = "title", required = false) String title,
                @RequestParam(value = "subTitle", required = false) String subTitle,
                @RequestParam(value = "image", required = false) MultipartFile image
        ) throws IOException {

            SliderRequestDto dto = new SliderRequestDto();
            dto.setTitle(title);
            dto.setSubTitle(subTitle);

            if (image != null) {
                String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "sliders" + File.separator;
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                File dest = new File(uploadDir + fileName);
                image.transferTo(dest);

                dto.setImages("/uploads/sliders/" + fileName);
            }

            return service. updateSliderPartial(id, dto);
        }


        @DeleteMapping("/deleteSlider/{id}")
        public String deleteSlider(@PathVariable Long id) {
            service.softDeleteSlider(id);
            return "Slider with id " + id + " marked as INACTIVE.";
        }



    }


