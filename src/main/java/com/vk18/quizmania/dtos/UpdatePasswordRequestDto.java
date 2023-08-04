package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class UpdatePasswordRequestDto {
    private String phone;
    private String oldPassword;
    private String newPassword;
}
