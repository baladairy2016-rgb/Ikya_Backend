package com.vidhuras.hospital_app.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="ambulances")
@Getter
@Setter
public class Ambulance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ambulance_id")
    private Long ambulanceId;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "driver_phone")
    private String driverPhone;

    @Column(name = "current_lat")
    private Double currentLat;

    @Column(name = "current_lng")
    private Double currentLng;

    @Column(name = "ambulanceStatus")
    private String ambulanceStatus;

    @Column(name = "status")
    private String status; // ACTIVE / INACTIVE

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_ip")
    private String createdIp;

    @Column(name = "modified_ip")
    private String modifiedIp;
}

