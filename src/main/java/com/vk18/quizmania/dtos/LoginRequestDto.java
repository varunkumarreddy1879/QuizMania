package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String phone;
    private String password;
}
