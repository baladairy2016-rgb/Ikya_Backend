package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FacilitiesResponseDto {
    private Long id;
    private String title;
    private String image;
    private String icon;
    private Boolean status;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
