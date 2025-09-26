package com.vidhuras.hospital_app.Service;

import com.vidhuras.hospital_app.Dto.LoginRequest;
import com.vidhuras.hospital_app.Dto.UserRequestDto;
import com.vidhuras.hospital_app.Dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);

    UserResponseDto updateUser(Long id, UserRequestDto dto);

    void deleteUser(Long id);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto login(LoginRequest dto);
}
