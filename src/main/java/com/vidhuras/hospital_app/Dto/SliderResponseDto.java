package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class SliderResponseDto {
    private Long id;
    private String title;
    private String subTitle;
    private String images;
    private LocalDateTime createdOn;
    private String createdIp;
    private String createdAt;
    private LocalDateTime modifiedOn;
    private String modifiedIp;
    private String modifiedAt;
    private String status;
}
