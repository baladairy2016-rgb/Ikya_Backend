package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class AboutResponseDto {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private LocalDateTime createdOn;
    private String createdIp;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedIp;
    private String modifiedBy;

}
