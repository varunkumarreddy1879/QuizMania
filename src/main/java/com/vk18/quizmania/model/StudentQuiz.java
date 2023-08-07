package com.vk18.quizmania.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class StudentQuiz extends BaseModel {
    @ManyToOne
    private Student student;
    @ManyToOne
    private Quiz quiz;
    private int score;
}
