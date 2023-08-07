package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class DeleteQuizRequestDto {
    private Long instructorId;
    private Long quizId;
}
