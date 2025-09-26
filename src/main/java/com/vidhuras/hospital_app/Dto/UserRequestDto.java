package com.vidhuras.hospital_app.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String fullName;
    private String email;
    private String password;
    private String role;
    private String phone;
}
