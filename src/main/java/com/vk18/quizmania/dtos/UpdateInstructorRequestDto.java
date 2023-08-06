package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class UpdateInstructorRequestDto {
    private Long instructorId;
    private String name;
    private String phone;
    private String password;
}
