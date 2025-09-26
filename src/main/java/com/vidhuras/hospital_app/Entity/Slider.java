package com.vidhuras.hospital_app.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="Sliders")
public class Slider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "images")
    private String images;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_ip")
    private String createdIp;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "modified_ip")
    private String modifiedIp;

    @Column(name = "modified_at")
    private String modifiedAt;

    @Column(name = "status")
    private String status;

}