package com.vk18.quizmania.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type", discriminatorType = DiscriminatorType.STRING)
public class Question extends BaseModel{
    private String description;
    private int points;
    @Enumerated(EnumType.ORDINAL)
    private DifficultyLevel difficultyLevel;
    @ManyToOne
    private Instructor createdBy;
    @Enumerated(EnumType.ORDINAL)
    private QuestionType type;
}