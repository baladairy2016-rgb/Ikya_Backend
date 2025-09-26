package com.vidhuras.hospital_app.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IkyaServiceRquestDto {
    private Long id;

    @JsonProperty("serviceName")
    private String serviceName;

    private String image;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdOn;

    private int popularService;
    private String createdIp;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedIp;
    private String modifiedBy;
    private String status;
}
