package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.DifficultyLevel;
import lombok.Data;

import java.util.List;

@Data
public class DeleteQuestionResponseSto {
    private String question;
    private DifficultyLevel difficultyLevel;
    private String answer;
    private List<ChoiceDto> choices;
}
