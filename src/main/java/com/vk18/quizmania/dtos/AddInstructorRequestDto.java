package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class AddInstructorRequestDto {
    private String name;
    private String phone;
    private String password;
}
