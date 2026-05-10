package com.mchiir.starterkit.modules.user.service;

import com.mchiir.starterkit.exception.DuplicateUserException;
import com.mchiir.starterkit.exception.InvalidCredentialsException;
import com.mchiir.starterkit.exception.ResourceNotFoundException;
import com.mchiir.starterkit.modules.user.dto.AuthRequest;
import com.mchiir.starterkit.modules.user.dto.AuthResponse;
import com.mchiir.starterkit.modules.user.dto.CreateUserRequest;
import com.mchiir.starterkit.modules.user.dto.UserDto;
import com.mchiir.starterkit.modules.user.entity.User;
import com.mchiir.starterkit.modules.user.mapper.UserMapper;
import com.mchiir.starterkit.modules.user.repository.UserRepository;
import com.mchiir.starterkit.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserDto createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateUserException("User with email already exists");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public AuthResponse authenticateUser(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        // verify password (hash compare) - throw InvalidCredentialsException on mismatch
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}