package com.mchiir.starterkit.auth;

import com.mchiir.starterkit.modules.user.dto.AuthRequest;
import com.mchiir.starterkit.modules.user.dto.AuthResponse;
import com.mchiir.starterkit.modules.user.dto.CreateUserRequest;
import com.mchiir.starterkit.modules.user.dto.UserDto;
import com.mchiir.starterkit.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody CreateUserRequest request) {
        // allow service to throw DuplicateUserException, etc.
        UserDto created = userService.createUser(request);
        return ResponseEntity.status(201).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        // AuthService throws InvalidCredentialsException when auth fails
        AuthResponse resp = userService.authenticateUser(request);
        return ResponseEntity.ok(resp);
    }
}