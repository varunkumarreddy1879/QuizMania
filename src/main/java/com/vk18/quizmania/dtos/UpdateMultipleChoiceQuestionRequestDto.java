package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.DifficultyLevel;
import lombok.Data;

import java.util.List;

@Data
public class UpdateMultipleChoiceQuestionRequestDto {
    private Long questionId;
    private Long instructorId;
    private String description;
    private int points;
    private DifficultyLevel difficultyLevel;
    private String correctAnswer;
    private List<OptionDto> options;
}
