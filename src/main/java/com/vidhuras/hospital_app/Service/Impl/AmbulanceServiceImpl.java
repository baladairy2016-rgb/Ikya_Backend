package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Dto.AmbulanceRequestDto;
import com.vidhuras.hospital_app.Dto.AmbulanceResponseDto;
import com.vidhuras.hospital_app.Dto.AppointmentResponseDto;
import com.vidhuras.hospital_app.Entity.Ambulance;
import com.vidhuras.hospital_app.Entity.Appointment;
import com.vidhuras.hospital_app.Repository.AmbulanceRepository;
import com.vidhuras.hospital_app.Service.AmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
    public class AmbulanceServiceImpl implements AmbulanceService {

        @Autowired
        private AmbulanceRepository repository;


        @Override
        public AmbulanceResponseDto createService(AmbulanceRequestDto dto) {
            Ambulance ambulance = new Ambulance();
            ambulance.setVehicleNumber(dto.getVehicleNumber());
            ambulance.setDriverName(dto.getDriverName());
            ambulance.setDriverPhone(dto.getDriverPhone());
            ambulance.setCurrentLat(dto.getCurrentLat());
            ambulance.setCurrentLng(dto.getCurrentLng());
            ambulance.setAmbulanceStatus(dto.getAmbulanceStatus());

            ambulance.setStatus("ACTIVE");
            ambulance.setCreatedOn(LocalDateTime.now());

            Ambulance saved = repository.save(ambulance);
            return mapToDto(saved);
        }

        private AmbulanceResponseDto mapToDto(Ambulance ambulance) {
            AmbulanceResponseDto dto = new AmbulanceResponseDto();
            dto.setAmbulanceId(ambulance.getAmbulanceId());
            dto.setDriverName(ambulance.getDriverName());
            dto.setDriverPhone(ambulance.getDriverPhone());
            dto.setVehicleNumber(ambulance.getVehicleNumber());
            dto.setCurrentLat(ambulance.getCurrentLat());
            dto.setCurrentLng(ambulance.getCurrentLng());
            dto.setAmbulanceStatus(ambulance.getAmbulanceStatus());

            dto.setStatus(ambulance.getStatus());
            dto.setCreatedOn(ambulance.getCreatedOn());
            dto.setModifiedOn(ambulance.getModifiedOn());
            return dto;
        }


        @Override
        public AmbulanceResponseDto updateAmbulance(Long id, AmbulanceRequestDto dto) {
            Ambulance ambulance = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ambulance not found"));

            ambulance.setVehicleNumber(dto.getVehicleNumber());
            ambulance.setDriverName(dto.getDriverName());
            ambulance.setDriverPhone(dto.getDriverPhone());
            ambulance.setCurrentLat(dto.getCurrentLat());
            ambulance.setCurrentLng(dto.getCurrentLng());
            ambulance.setAmbulanceStatus(dto.getAmbulanceStatus());
            ambulance.setStatus(dto.getStatus());
            ambulance.setModifiedBy(dto.getCreatedBy());
            ambulance.setModifiedIp(dto.getCreatedIp());
            ambulance.setModifiedOn(LocalDateTime.now());

            return mapToDto(repository.save(ambulance));
        }

        @Override
        public AmbulanceResponseDto getAmbulanceById(Long id) {
            Ambulance ambulance = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ambulance not found"));
            return mapToDto(ambulance);
        }

        @Override
        public List<AmbulanceResponseDto> getAllAmbulances() {
            return repository.findAll().stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }

        @Override
        public void deleteAmbulance(Long id) {
            repository.deleteById(id);
        }
    }
