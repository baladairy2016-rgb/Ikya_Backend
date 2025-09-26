package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Dto.LoginRequest;
import com.vidhuras.hospital_app.Dto.UserRequestDto;
import com.vidhuras.hospital_app.Dto.UserResponseDto;
import com.vidhuras.hospital_app.Entity.User;
import com.vidhuras.hospital_app.Repository.UserRepository;
import com.vidhuras.hospital_app.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private UserResponseDto mapToResponse(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .phone(user.getPhone())
                .status(user.getStatus().name())
                .build();
    }

    @Override
    public UserResponseDto login(LoginRequest dto) {
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return mapToResponse(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(dto.getPassword()) // (for real apps use BCrypt)
                .role(dto.getRole())
                .phone(dto.getPhone())
                .status(User.Status.ACTIVE)
                .build();
        return mapToResponse(repository.save(user));
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = repository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found with id " + id));

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setPhone(dto.getPhone());

        return mapToResponse(repository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

}
