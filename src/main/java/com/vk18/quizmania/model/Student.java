package com.vk18.quizmania.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Student extends BaseModel{
    private String name;
    private String phone;
    private String password;
}
