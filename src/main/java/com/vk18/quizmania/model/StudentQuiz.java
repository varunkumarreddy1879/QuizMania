package com.vk18.quizmania.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class StudentQuiz extends BaseModel {
    private Student student;
    private Quiz quiz;
    private Double score;
}
