package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.QuestionResponseDto;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Question;
import com.vk18.quizmania.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService=questionService;
    }

    @GetMapping("/get/{questionId}")
    public ResponseEntity<QuestionResponseDto> getQuestion(@PathVariable Long quesionId){
        try{
            Question question=questionService.getQuestion(quesionId);
            return ResponseEntity.status(HttpStatus.OK).body(questionToResponseMapper(question));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll/{instructorId}")
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestions(@PathVariable Long instructorId){

        try{
            List<Question> questions=questionService.getAllQuestions(instructorId);
            List<QuestionResponseDto> responseDtos=new ArrayList<>();
            for(Question question:questions){
                responseDtos.add(questionToResponseMapper(question));
            }
            return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public QuestionResponseDto questionToResponseMapper(Question question){
        QuestionResponseDto questionResponseDto=new QuestionResponseDto();
        questionResponseDto.setCreatedBy(question.getCreatedBy().getName());
        questionResponseDto.setQuestionId(question.getId());
        questionResponseDto.setDescription(question.getDescription());
        questionResponseDto.setPoints(question.getPoints());
        questionResponseDto.setQuestionType(question.getType());


        return questionResponseDto;
    }
}
