package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String name;
    private String phone;
    private String password;
}
