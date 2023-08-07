package com.vk18.quizmania.dtos;

import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.Question;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
public class QuizResponseDto {
    private Long quizId;
    private String quizDescription;
    private String instructorName;
    private List<Long> questionIds;
    private String category;
}
