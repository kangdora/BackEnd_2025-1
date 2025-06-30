package com.example.bcsd.service;

import com.example.bcsd.config.JwtUtil;
import com.example.bcsd.dto.SignupRequestDto;
import com.example.bcsd.exception.common.BaseException;
import com.example.bcsd.exception.common.ErrorCode;
import com.example.bcsd.model.USER_ROLES;
import com.example.bcsd.model.User;
import com.example.bcsd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BaseException(ErrorCode.PASSWORD_MISMATCH);
        }

        return jwtUtil.createToken(user.getEmail(), user.getRoles().name());
    }


    @Transactional
    public void signup(SignupRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(USER_ROLES.USER)
                .build();

        userRepository.save(user);
    }
}
