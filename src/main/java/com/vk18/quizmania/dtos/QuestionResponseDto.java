package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.DifficultyLevel;
import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class QuestionResponseDto {
    private Long questionId;
    private String description;
    private int points;
    private DifficultyLevel difficultyLevel;
    private String createdBy;
    private QuestionType questionType;
}
