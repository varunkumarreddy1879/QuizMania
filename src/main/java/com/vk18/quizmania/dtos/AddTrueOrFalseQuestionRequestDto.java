package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.DifficultyLevel;
import lombok.Data;

@Data
public class AddTrueOrFalseQuestionRequestDto {
    private String question;
    private DifficultyLevel difficultyLevel;
    private String answer;
    private Long userId;
}
