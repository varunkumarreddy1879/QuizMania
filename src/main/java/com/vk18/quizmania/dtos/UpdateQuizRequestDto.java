package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class UpdateQuizRequestDto {
    private Long quizId;
    private Long instructorId;
    private String quizDescription;
    private String category;
}
