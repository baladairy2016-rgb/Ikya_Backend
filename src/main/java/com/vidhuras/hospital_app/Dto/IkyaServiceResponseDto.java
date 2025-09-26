package com.vidhuras.hospital_app.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class IkyaServiceResponseDto {

    private Long id;
    private String serviceName;
    private String image;
    private String description;
    private int popularService;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdOn;

    private String createdIp;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedIp;
    private String modifiedBy;
    private String status;
}

