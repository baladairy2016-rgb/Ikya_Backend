package com.vidhuras.hospital_app.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChooseUsRequestDto {
    private Long id;

    @JsonProperty("serviceName")
    private String serviceName;

    private String description;
    private String image;
    private String icon;
    private String status;
    private LocalDateTime createdOn;
    private String createdIp;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedIp;
    private String modifiedBy;
}
