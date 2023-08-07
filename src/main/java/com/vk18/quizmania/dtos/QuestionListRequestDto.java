package com.vk18.quizmania.dtos;

import lombok.Data;

import java.util.List;

@Data
public class QuestionListRequestDto {
    private Long quizId;
    private Long instructorId;
    private List<Long> questionIds;
}
