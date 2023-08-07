package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.Question;
import lombok.Data;

import java.util.List;

@Data
public class SubmitQuizRequestDto {
    private Long studentId;
    private Long quizId;
    private List<QuestionAnswer> responses;
}
