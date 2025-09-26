package com.vidhuras.hospital_app.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatientRequestDto {
    private Long patientId;
    private String firstName;
    private String lastName;
    private String dob;
    private String phone;
    private String age;
    private String bloodGroup;
    private String email;
    private String gender;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String emergencyContactName;
    private String emergencyContactNumber;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedOn;
    private String createdIp;
    private String createdBy;


    private String modifiedIp;
    private String modifiedBy;
    private String status;
}
