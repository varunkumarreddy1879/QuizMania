package com.vk18.quizmania.model;

import jakarta.persistence.*;
import lombok.Data;
import com.vk18.quizmania.model.Option;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("multiple_choice")
public class MultipleChoiceQuestion extends Question {
    private String correctAnswer;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Option> options;
}
