package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.AddMultipleChoiceQuestionRequestDto;
import com.vk18.quizmania.dtos.AddTrueOrFalseQuestionRequestDto;
import com.vk18.quizmania.dtos.ChoiceDto;
import com.vk18.quizmania.dtos.DeleteQuestionResponseSto;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Choice;
import com.vk18.quizmania.model.DifficultyLevel;
import com.vk18.quizmania.model.Question;
import com.vk18.quizmania.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addQuestion/multiple-choice")
    public ResponseEntity<String> addMultipleChoiceQuestion(@RequestBody AddMultipleChoiceQuestionRequestDto requestDto){

        Long userId=requestDto.getUserId();
        String question = requestDto.getQuestion();

        List<Choice> choices=new ArrayList<>();
        for(ChoiceDto choiceDto:requestDto.getChoices()){
            Choice choice=new Choice();
            choice.setValue(choiceDto.getValue());
            choices.add(choice);
        }

        String answer=requestDto.getAnswer();
        DifficultyLevel difficultyLevel=requestDto.getDifficultyLevel();

        try{
            Question addedQuestion=questionService.addMultipleChoiceQuestion(userId,question,choices,answer,difficultyLevel);
            return new ResponseEntity<>("Question added succesfully with id : "+addedQuestion.getId(), HttpStatusCode.valueOf(400));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating question.");
        }

    }

    @PostMapping("//addQuestion/True-False")
    public ResponseEntity<String> addTrueOrFalseQuestion(@RequestBody AddTrueOrFalseQuestionRequestDto requestDto){
        Long userId=requestDto.getUserId();
        String question = requestDto.getQuestion();
        String answer=requestDto.getAnswer();
        DifficultyLevel difficultyLevel=requestDto.getDifficultyLevel();

        try{
            Question addedQuestion=questionService.addTrueOrFalseQuestion(userId,question,answer,difficultyLevel);
            return new ResponseEntity<>("Question added succesfully with id : "+addedQuestion.getId(), HttpStatusCode.valueOf(400));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating question.");
        }
    }

    @DeleteMapping("/delete/{userId}/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long userId,@PathVariable Long questionId){

        try{
            Question question=questionService.deleteQuestion(userId,questionId);

            DeleteQuestionResponseSto responseDto=new DeleteQuestionResponseSto();
            responseDto.setQuestion(question.getQuestion());
            responseDto.setAnswer(question.getAnswer());
            responseDto.setDifficultyLevel(question.getDifficultyLevel());

            return ResponseEntity.status(HttpStatus.OK).body(responseDto.toString()+"deleted successfully.");
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating question.");
        }
    }


}
