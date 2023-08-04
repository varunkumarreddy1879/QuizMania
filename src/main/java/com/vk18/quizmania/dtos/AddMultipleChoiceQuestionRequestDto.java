package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.DifficultyLevel;
import com.vk18.quizmania.model.Question;
import com.vk18.quizmania.model.User;
import lombok.Data;

import java.util.List;

@Data
public class AddMultipleChoiceQuestionRequestDto {
    private String question;
    private DifficultyLevel difficultyLevel;
    private String answer;
    private Long userId;

    private List<ChoiceDto> choices;
}
