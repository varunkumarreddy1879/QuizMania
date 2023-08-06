package com.vk18.quizmania.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "Optiozs")
public class Option extends BaseModel{
    private String value;
}
