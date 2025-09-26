package com.vidhuras.hospital_app.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

    @Entity
    @Getter
    @Setter
    @Data
    @Table(name = "doctors")
    public class Doctor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "doctor_id")
        private Long doctorId;

        @Column(name = "doctorName", nullable = false)
        private String doctorName;

        @Column(name = "profile_image")
        private String profileImage;

        @Column(name = "email")
        private String email;

        @Column(name = "phone")
        private String phone;

        @Column(name = "specialization")
        private String specialization;

        @Column(name = "location")
        private String location;

        @Column(name = "specialities")
        private String specialities;

        @Column(name = "experience")
        private String experience;

        @Column(name = "qualification")
        private String qualification;

        @Column(name = "gender")
        private String gender;

        @Column(name = "consultingFee")
        private String consultingFee;

        @Column(name = "availableDays")
        private String availableDays;


        @Column(name = "availableTime", columnDefinition = "TEXT")
        private String availableTime;

        @Column(name = "description")
        private String description;


        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @Column(name = "created_on")
        private LocalDateTime createdOn;

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @Column(name = "modified_on")
        private LocalDateTime modifiedOn;

        @Column(name = "created_by")
        private String createdBy;

        @Column(name = "created_ip")
        private String createdIp;



        @Column(name = "modified_by")
        private String modifiedBy;

        @Column(name = "modified_ip")
        private String modifiedIp;

        private String status;
    }
