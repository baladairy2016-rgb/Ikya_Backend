package com.vidhuras.hospital_app.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

    @Getter
    @Setter
    @Entity
    @Table(name="Blogs")
    public class Blog {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String title;

        private String image;
        private String icon;
        private String description;

        @Column(name = "status")
        private String status;

        private LocalDateTime createdOn;
        private String createdBy;
        private LocalDateTime modifiedOn;
        private String modifiedBy;
    }

