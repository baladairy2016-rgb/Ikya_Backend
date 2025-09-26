package com.vidhuras.hospital_app.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long userId;
    private String fullName;
    private String email;
    private String role;
    private String phone;
    private String status;
}
