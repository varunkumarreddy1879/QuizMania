package com.vk18.quizmania.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Choice extends BaseModel{
    private String value;
}
