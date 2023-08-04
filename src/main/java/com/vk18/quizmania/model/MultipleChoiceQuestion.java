package com.vk18.quizmania.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class MultipleChoiceQuestion extends Question {
    @OneToMany(fetch = FetchType.EAGER)
    private List<Choice> choices;
}
