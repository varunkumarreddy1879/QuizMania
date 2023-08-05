package com.vk18.quizmania.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Instructor extends BaseModel{
    private String name;
    private String phone;
    private String password;
    @OneToMany(mappedBy = "instructor")
    private List<Quiz> quizzes;
}
