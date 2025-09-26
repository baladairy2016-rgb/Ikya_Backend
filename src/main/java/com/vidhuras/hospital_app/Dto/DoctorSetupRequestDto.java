package com.vidhuras.hospital_app.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

    @Getter
    @Setter
    public class DoctorSetupRequestDto {
        private Long doctorId;

        @JsonProperty("doctorName")
        private String doctorName;
        private String description;
        private String email;
        private String phone;
        private String specialization;
        private String experience;
        private String qualification;
        private String gender;
        private String consultingFee;
        private String availableDays;
        private String availableTime;
        private String profileImage;
        private String specialities;
        private String location;

        private LocalDateTime createdOn;

        private LocalDateTime modifiedOn;

        private String createdIp;
        private String createdBy;
        private String modifiedIp;
        private String modifiedBy;
        private String status;
    }
