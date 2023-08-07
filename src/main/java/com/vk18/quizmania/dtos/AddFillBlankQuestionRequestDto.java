package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.DifficultyLevel;
import lombok.Data;

@Data
public class AddFillBlankQuestionRequestDto {
    private Long instructorId;
    private String description;
    private int points;
    private DifficultyLevel difficultyLevel;
    private String correctAnswer;

}
