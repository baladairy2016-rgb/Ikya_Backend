package com.vidhuras.hospital_app.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class AboutRequestDto {
    private Long id;

    @JsonProperty("title")
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
