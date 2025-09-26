package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Dto.AppointmentRequestDto;
import com.vidhuras.hospital_app.Dto.AppointmentResponseDto;
import com.vidhuras.hospital_app.Entity.Appointment;
import com.vidhuras.hospital_app.Entity.Doctor;
import com.vidhuras.hospital_app.Repository.AppointmentRepository;
import com.vidhuras.hospital_app.Repository.DoctorSetupRepository;
import com.vidhuras.hospital_app.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorSetupRepository doctorRepository;


    @Override
    public AppointmentResponseDto createAppointment(AppointmentRequestDto dto) {
        Appointment appointment = new Appointment();
        appointment.setDoctorId(dto.getDoctorId());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setMode(dto.getMode());
        appointment.setPatientName(dto.getPatientName());
        appointment.setPhoneNumber(dto.getPhoneNumber());
        appointment.setPaymentStatus(dto.getPaymentStatus() != null ? dto.getPaymentStatus() : "PENDING");
        appointment.setPaymentReference(dto.getPaymentReference());
        appointment.setStatus("ACTIVE");
        appointment.setCreatedOn(LocalDateTime.now());
        appointment.setAltPhone(dto.getAltPhone());
        appointment.setGender(dto.getGender());
        appointment.setAge(dto.getAge());
        appointment.setEmail(dto.getEmail());
        appointment.setLocation(dto.getLocation());

        Appointment saved = appointmentRepository.save(appointment);

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        appointment.setFee(Integer.parseInt(doctor.getConsultingFee()));
        // Example: send SMS to admin/doctor

        return mapToDto(saved);
    }

    @Override
    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDto getAppointmentById(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID " + appointmentId));
        return mapToDto(appointment);
    }

    @Override
    public AppointmentResponseDto updateAppointment(Long appointmentId, AppointmentRequestDto dto) {
        Appointment updated = appointmentRepository.findById(appointmentId).map(existing -> {
            existing.setDoctorId(dto.getDoctorId());
            existing.setAppointmentDate(dto.getAppointmentDate());
            existing.setAppointmentTime(dto.getAppointmentTime());
            existing.setMode(dto.getMode());
//            existing.setPatientName(dto.getPatientName());
//            existing.setPhoneNumber(dto.getPhoneNumber());
            existing.setPaymentStatus(dto.getPaymentStatus());
            existing.setPaymentReference(dto.getPaymentReference());
            existing.setStatus(dto.getStatus() != null ? dto.getStatus() : existing.getStatus());
            existing.setModifiedOn(LocalDateTime.now());

            // Patient-related fields
            existing.setAltPhone(dto.getAltPhone());
            existing.setGender(dto.getGender());
            existing.setAge(dto.getAge());
            existing.setEmail(dto.getEmail());
            existing.setLocation(dto.getLocation());

            return appointmentRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Appointment not found with ID " + appointmentId));

        return mapToDto(updated);
    }


    @Override
    public void deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID " + appointmentId));
        appointment.setStatus("INACTIVE");
        appointment.setModifiedOn(LocalDateTime.now());
        appointmentRepository.save(appointment);
    }

    private AppointmentResponseDto mapToDto(Appointment appointment) {
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setAppointmentId(appointment.getAppointmentId());
        dto.setDoctorId(appointment.getDoctorId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setMode(appointment.getMode());
        dto.setPatientName(appointment.getPatientName());
        dto.setPhoneNumber(appointment.getPhoneNumber());
        dto.setPaymentStatus(appointment.getPaymentStatus());
        dto.setPaymentReference(appointment.getPaymentReference());
        dto.setStatus(appointment.getStatus());
        dto.setCreatedOn(appointment.getCreatedOn());
        dto.setModifiedOn(appointment.getModifiedOn());

        dto.setAltPhone(appointment.getAltPhone());
        dto.setGender(appointment.getGender());
        dto.setAge(appointment.getAge());
        dto.setEmail(appointment.getEmail());
        dto.setLocation(appointment.getLocation());
        return dto;
    }
}
