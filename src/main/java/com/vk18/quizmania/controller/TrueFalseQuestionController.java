package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.*;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.DifficultyLevel;
import com.vk18.quizmania.model.TrueFalseQuestion;
import com.vk18.quizmania.service.TrueFalseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/true-false")
public class TrueFalseQuestionController {
    TrueFalseQuestionService trueFalseQuestionService;

    @Autowired
    public TrueFalseQuestionController(TrueFalseQuestionService trueFalseQuestionService){
        this.trueFalseQuestionService=trueFalseQuestionService;
    }
    @PostMapping("/add")
    public ResponseEntity<TrueFalseResponseDto> add(@RequestBody AddTrueFalseQuestionRequestDto requestDto){
        Long instructorId=requestDto.getInstructorId();
        String description=requestDto.getDescription();
        DifficultyLevel difficultyLevel=requestDto.getDifficultyLevel();
        boolean correctAnswer=requestDto.isCorrectAnswer();
        int points=requestDto.getPoints();

        try{
            TrueFalseQuestion trueFalseQuestion=trueFalseQuestionService.add(instructorId,description,difficultyLevel,correctAnswer,points);
            return ResponseEntity.status(HttpStatus.OK).body(trueFalseQuestionToResponseMapper(trueFalseQuestion));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<TrueFalseResponseDto> update(@RequestBody updateTrueFalseQuestionRequestDto requestDto){
        Long questionId= requestDto.getQuestionId();
        Long instructorId=requestDto.getInstructorId();
        String description=requestDto.getDescription();
        DifficultyLevel difficultyLevel=requestDto.getDifficultyLevel();
        int points=requestDto.getPoints();
        boolean correctAnswer= requestDto.isCorrectAnswer();

        try{
            TrueFalseQuestion trueFalseQuestion=trueFalseQuestionService.update(questionId,instructorId,description,difficultyLevel,correctAnswer,points);
            return ResponseEntity.status(HttpStatus.OK).body(trueFalseQuestionToResponseMapper(trueFalseQuestion));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<TrueFalseResponseDto> delete(@PathVariable Long questionId){
        try{
            TrueFalseQuestion trueFalseQuestion=trueFalseQuestionService.delete(questionId);
            return ResponseEntity.status(HttpStatus.OK).body(trueFalseQuestionToResponseMapper(trueFalseQuestion));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get/{questionId}")
    public ResponseEntity<TrueFalseResponseDto> getQuestionById(@PathVariable Long questionId){
        try{
            TrueFalseQuestion trueFalseQuestion=trueFalseQuestionService.getQuestionById(questionId);
            return ResponseEntity.status(HttpStatus.OK).body(trueFalseQuestionToResponseMapper(trueFalseQuestion));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TrueFalseResponseDto>> getAllQuestions(){

        try{
            List<TrueFalseQuestion> trueFalseQuestions=trueFalseQuestionService.getAllQuestions();
            List<TrueFalseResponseDto> responseDtos=new ArrayList<>();
            for(TrueFalseQuestion trueFalseQuestion:trueFalseQuestions){
                responseDtos.add(trueFalseQuestionToResponseMapper(trueFalseQuestion));
            }
            return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public TrueFalseResponseDto trueFalseQuestionToResponseMapper(TrueFalseQuestion trueFalseQuestion){
        TrueFalseResponseDto trueFalseResponseDto=new TrueFalseResponseDto();
        trueFalseResponseDto.setInstructorName(trueFalseQuestion.getCreatedBy().getName());
        trueFalseResponseDto.setQuestionId(trueFalseQuestion.getId());
        trueFalseResponseDto.setDescription(trueFalseQuestion.getDescription());
        trueFalseResponseDto.setPoints(trueFalseQuestion.getPoints());
        trueFalseResponseDto.setCorrectAnswer(trueFalseQuestion.isCorrectAnswer());


        return trueFalseResponseDto;
    }
}
