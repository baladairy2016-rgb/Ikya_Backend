package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AmbulanceRequestDto {

    private String vehicleNumber;
    private String driverName;
    private String driverPhone;

    private Double currentLat;
    private Double currentLng;

    private String ambulanceStatus;
    private String status;
    private LocalDateTime createdOn;
    private String createdBy;
    private String createdIp;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
    private String modifiedIp;




}
