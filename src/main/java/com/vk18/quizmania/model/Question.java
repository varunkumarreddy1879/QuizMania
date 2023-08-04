package com.vk18.quizmania.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Question extends BaseModel{
    private String question;
    @Enumerated(EnumType.ORDINAL)
    private DifficultyLevel difficultyLevel;
    private String answer;
    @ManyToOne
    private User createdBy;
}
