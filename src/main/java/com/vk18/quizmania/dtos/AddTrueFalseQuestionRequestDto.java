package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.DifficultyLevel;
import lombok.Data;

import java.util.List;

@Data
public class AddTrueFalseQuestionRequestDto {
    private Long instructorId;
    private String description;
    private int points;
    private DifficultyLevel difficultyLevel;
    private boolean correctAnswer;
}
