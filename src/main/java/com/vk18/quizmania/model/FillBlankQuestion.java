package com.vk18.quizmania.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("Fill-Blank")
public class FillBlankQuestion extends Question {
    private String correctAnswer;
}
