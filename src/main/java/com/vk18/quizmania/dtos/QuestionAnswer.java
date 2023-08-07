package com.vk18.quizmania.dtos;

import lombok.Data;

@Data
public class QuestionAnswer {
    private Long questionId;
    private String submittedAnswer;
}
