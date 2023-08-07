package com.vk18.quizmania.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@DiscriminatorValue("True_False")
public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;
}

