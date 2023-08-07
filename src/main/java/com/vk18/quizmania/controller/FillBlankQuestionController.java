package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.*;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.DifficultyLevel;
import com.vk18.quizmania.model.FillBlankQuestion;
import com.vk18.quizmania.model.QuestionType;
import com.vk18.quizmania.service.FillBlankQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fill-blank")
public class FillBlankQuestionController {
    FillBlankQuestionService fillBlankQuestionService;

    @Autowired
    public FillBlankQuestionController(FillBlankQuestionService fillBlankQuestionService){
        this.fillBlankQuestionService=fillBlankQuestionService;
    }

    @PostMapping("/add")
    public ResponseEntity<FillBlankResponseDto> add(@RequestBody AddFillBlankQuestionRequestDto requestDto){
        Long instructorId=requestDto.getInstructorId();
        String description=requestDto.getDescription();
        DifficultyLevel difficultyLevel=requestDto.getDifficultyLevel();
        String correctAnswer=requestDto.getCorrectAnswer();
        int points=requestDto.getPoints();
        QuestionType questionType=QuestionType.Fill_Blank;

        try{
            FillBlankQuestion fillBlankQuestion=fillBlankQuestionService.add(instructorId,description,difficultyLevel,correctAnswer,points,questionType);
            return ResponseEntity.status(HttpStatus.OK).body(fillBlankQuestiontoResponseMapper(fillBlankQuestion));
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
    public ResponseEntity<FillBlankResponseDto> update(@RequestBody UpdateFillBlankRequestDto requestDto){
        Long questionId= requestDto.getQuestionId();
        Long instructorId=requestDto.getInstructorId();
        String description=requestDto.getDescription();
        DifficultyLevel difficultyLevel=requestDto.getDifficultyLevel();
        int points=requestDto.getPoints();
        String correctAnswer= requestDto.getCorrectAnswer();

        try{
            FillBlankQuestion fillBlankQuestion=fillBlankQuestionService.update(questionId,instructorId,description,difficultyLevel,correctAnswer,points);
            return ResponseEntity.status(HttpStatus.OK).body(fillBlankQuestiontoResponseMapper(fillBlankQuestion));
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
    public ResponseEntity<FillBlankResponseDto> delete(@PathVariable Long questionId){
        try{
            FillBlankQuestion fillBlankQuestion=fillBlankQuestionService.delete(questionId);
            return ResponseEntity.status(HttpStatus.OK).body(fillBlankQuestiontoResponseMapper(fillBlankQuestion));
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
    public ResponseEntity<FillBlankResponseDto> getQuestionById(@PathVariable Long questionId){
        try{
            FillBlankQuestion fillBlankQuestion=fillBlankQuestionService.getQuestionById(questionId);
            return ResponseEntity.status(HttpStatus.OK).body(fillBlankQuestiontoResponseMapper(fillBlankQuestion));
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
    public ResponseEntity<List<FillBlankResponseDto>> getAllQuestions(@PathVariable Long instructorId){

        try{
            List<FillBlankQuestion> fillBlankQuestions=fillBlankQuestionService.getAllQuestions(instructorId);
            List<FillBlankResponseDto> responseDtos=new ArrayList<>();
            for(FillBlankQuestion fillBlankQuestion:fillBlankQuestions){
                responseDtos.add(fillBlankQuestiontoResponseMapper(fillBlankQuestion));
            }
            return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public FillBlankResponseDto fillBlankQuestiontoResponseMapper(FillBlankQuestion fillBlankQuestion){
        FillBlankResponseDto fillBlankResponseDto=new FillBlankResponseDto();
        fillBlankResponseDto.setInstructorName(fillBlankQuestion.getCreatedBy().getName());
        fillBlankResponseDto.setQuestionId(fillBlankQuestion.getId());
        fillBlankResponseDto.setDescription(fillBlankQuestion.getDescription());
        fillBlankResponseDto.setPoints(fillBlankQuestion.getPoints());
        fillBlankResponseDto.setCorrectAnswer(fillBlankQuestion.getCorrectAnswer());
        fillBlankResponseDto.setQuestionType(fillBlankQuestion.getType());


        return fillBlankResponseDto;
    }
}
