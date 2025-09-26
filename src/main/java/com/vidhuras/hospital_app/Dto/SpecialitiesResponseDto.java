package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SpecialitiesResponseDto {
    private Long id;
    private String title;
    private String icon;
    private String image;
    private String image1;
    private String description;
    private Boolean status;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
