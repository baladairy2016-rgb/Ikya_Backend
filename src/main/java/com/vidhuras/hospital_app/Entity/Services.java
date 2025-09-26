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
    @Table(name = "services")
    public class Services {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "service_name", nullable = false)
        private String serviceName;

        @Column(name = "image")
        private String image;

        @Column(name = "description")
        private String description;

        @Column(name="pop_services")
        private int popularService;

        @JsonFormat(pattern = "yyyy-MM-dd")
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

