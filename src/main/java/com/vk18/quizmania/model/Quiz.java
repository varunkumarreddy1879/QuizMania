package com.vk18.quizmania.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz extends BaseModel{
    @ManyToOne
    private Instructor instructor;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Question> questions;
    private String category;

}
