package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Dto.AppointmentRequestDto;
import com.vidhuras.hospital_app.Dto.AppointmentResponseDto;
import com.vidhuras.hospital_app.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(
            @RequestBody AppointmentRequestDto dto) {
        return ResponseEntity.ok(appointmentService.createAppointment(dto));

    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(appointmentId));
    }


//    @PutMapping("/{appointmentId}")
//    public ResponseEntity<AppointmentResponseDto> updateAppointment(
//            @PathVariable Long appointmentId,
//            @RequestBody AppointmentRequestDto dto) {
//        return ResponseEntity.ok(appointmentService.updateAppointment(appointmentId, dto));
//    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(
            @PathVariable Long appointmentId,
            @RequestBody AppointmentRequestDto dto) {
        AppointmentResponseDto response = appointmentService.updateAppointment(appointmentId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok("Appointment status updated to INACTIVE for id " + appointmentId);
    }
}



