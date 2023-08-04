package com.vk18.quizmania.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class UserQuiz extends BaseModel{
    @ManyToOne
    private User user;
    @ManyToOne
    private Quiz quiz;
    private int result;
}
