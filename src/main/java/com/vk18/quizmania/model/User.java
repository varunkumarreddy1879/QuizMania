package com.vk18.quizmania.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User extends BaseModel{
    private String name;
    private String phone;
    private String password;
    @OneToMany(mappedBy = "createdBy",fetch = FetchType.EAGER)
    private List<Quiz> createdQuizzes;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Quiz> attemptedQuizzes;
}
