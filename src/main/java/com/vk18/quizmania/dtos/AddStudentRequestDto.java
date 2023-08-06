package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class AddStudentRequestDto {
    private String name;
    private String phone;
    private String password;
}
