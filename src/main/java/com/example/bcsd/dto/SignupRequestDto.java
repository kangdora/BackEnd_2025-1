package com.example.bcsd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String name;
    private String email;
    private String phone;
    private String password;
}