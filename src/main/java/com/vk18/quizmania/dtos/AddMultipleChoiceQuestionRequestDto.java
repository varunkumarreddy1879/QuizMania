package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.DifficultyLevel;
import com.vk18.quizmania.model.Option;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class AddMultipleChoiceQuestionRequestDto {
    private Long instructorId;
    private String description;
    private int points;
    private DifficultyLevel difficultyLevel;
    private String correctAnswer;
    private List<OptionDto> options;
}
