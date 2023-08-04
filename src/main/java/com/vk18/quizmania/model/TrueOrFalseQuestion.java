package com.vk18.quizmania.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
public class TrueOrFalseQuestion extends  Question{

    private List<String> options;
    public TrueOrFalseQuestion(){
        options.add("TRUE");
        options.add("FALSE");
    }
}
