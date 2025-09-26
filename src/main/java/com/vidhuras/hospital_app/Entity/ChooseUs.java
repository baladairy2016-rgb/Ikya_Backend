package com.vidhuras.hospital_app.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="choose_us")
public class ChooseUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "image")
    private String image;

    @Column(name = "designation")
    private String designation;

    @Column(name = "icon")
    private String icon;

    @Column(name = "description")
    private String description;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_ip")
    private String createdIp;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_ip")
    private String modifiedIp;

    private String status;
}
