package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.DifficultyLevel;
import com.vk18.quizmania.model.QuestionType;
import lombok.Data;

@Data
public class FillBlankResponseDto {
    private String instructorName;
    private Long questionId;
    private String description;
    private int points;
    private DifficultyLevel difficultyLevel;
    private String correctAnswer;
    private QuestionType questionType;
}
