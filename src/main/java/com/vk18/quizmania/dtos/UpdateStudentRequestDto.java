package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class UpdateStudentRequestDto {
    private Long studentId;
    private String name;
    private String phone;
    private String password;
}
