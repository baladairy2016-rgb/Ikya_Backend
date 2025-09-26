package com.vidhuras.hospital_app.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequestDto {
    private String title;
    private String image;
    private String icon;
    private String description;
    private Boolean status;
}
