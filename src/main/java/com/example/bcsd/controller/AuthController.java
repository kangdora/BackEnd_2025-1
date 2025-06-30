package com.example.bcsd.controller;

import com.example.bcsd.dto.LoginRequestDto;
import com.example.bcsd.dto.SignupRequestDto;
import com.example.bcsd.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        String token = authService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequestDto dto) {
        authService.signup(dto);
        return ResponseEntity.ok().build();
    }
}
