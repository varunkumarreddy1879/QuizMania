package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.AddMultipleChoiceQuestionRequestDto;
import com.vk18.quizmania.dtos.MultipleChoiceResponseDto;
import com.vk18.quizmania.dtos.OptionDto;
import com.vk18.quizmania.dtos.UpdateMultipleChoiceQuestionRequestDto;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.DifficultyLevel;
import com.vk18.quizmania.model.MultipleChoiceQuestion;
import com.vk18.quizmania.model.Option;
import com.vk18.quizmania.service.MultipleChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/multiple-choice")
public class MultipleChoiceQuestionController {
    MultipleChoiceQuestionService multipleChoiceQuestionService;

    @Autowired
    public MultipleChoiceQuestionController(MultipleChoiceQuestionService multipleChoiceQuestionService){
        this.multipleChoiceQuestionService=multipleChoiceQuestionService;
    }
    @PostMapping("/add")
    public ResponseEntity<MultipleChoiceResponseDto> add(@RequestBody AddMultipleChoiceQuestionRequestDto requestDto){
        Long instructorId=requestDto.getInstructorId();
        String description=requestDto.getDescription();
        DifficultyLevel difficultyLevel=requestDto.getDifficultyLevel();
        String correctAnswer=requestDto.getCorrectAnswer();
        int points=requestDto.getPoints();
        List<Option> options=new ArrayList<>();
        for(OptionDto optionDto:requestDto.getOptions()){
            Option option=new Option();
            option.setValue(optionDto.getValue());
        }

        try{
            MultipleChoiceQuestion multipleChoiceQuestion=multipleChoiceQuestionService.add(instructorId,description,difficultyLevel,correctAnswer,points,options);
            return ResponseEntity.status(HttpStatus.OK).body(multipleChoiceQuestionToResponseMapper(multipleChoiceQuestion));
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
    public ResponseEntity<MultipleChoiceResponseDto> update(@RequestBody UpdateMultipleChoiceQuestionRequestDto requestDto){
        Long questionId= requestDto.getQuestionId();
        Long instructorId=requestDto.getInstructorId();
        String description=requestDto.getDescription();
        DifficultyLevel difficultyLevel=requestDto.getDifficultyLevel();
        String correctAnswer=requestDto.getCorrectAnswer();
        int points=requestDto.getPoints();
        List<Option> options=new ArrayList<>();
        for(OptionDto optionDto:requestDto.getOptions()){
            Option option=new Option();
            option.setValue(optionDto.getValue());
        }

        try{
            MultipleChoiceQuestion multipleChoiceQuestion=multipleChoiceQuestionService.update(questionId,instructorId,description,difficultyLevel,correctAnswer,points,options);
            return ResponseEntity.status(HttpStatus.OK).body(multipleChoiceQuestionToResponseMapper(multipleChoiceQuestion));
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
    public ResponseEntity<MultipleChoiceResponseDto> delete(@PathVariable Long questionId){
        try{
            MultipleChoiceQuestion multipleChoiceQuestion=multipleChoiceQuestionService.delete(questionId);
            return ResponseEntity.status(HttpStatus.OK).body(multipleChoiceQuestionToResponseMapper(multipleChoiceQuestion));
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
    public ResponseEntity<MultipleChoiceResponseDto> getQuestionById(@PathVariable Long questionId){
        try{
            MultipleChoiceQuestion multipleChoiceQuestion=multipleChoiceQuestionService.getQuestionById(questionId);
            return ResponseEntity.status(HttpStatus.OK).body(multipleChoiceQuestionToResponseMapper(multipleChoiceQuestion));
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
    public ResponseEntity<List<MultipleChoiceResponseDto>> getAllQuestions(){

        try{
            List<MultipleChoiceQuestion> multipleChoiceQuestions=multipleChoiceQuestionService.getAllQuestions();
            List<MultipleChoiceResponseDto> responseDtos=new ArrayList<>();
            for(MultipleChoiceQuestion multipleChoiceQuestion:multipleChoiceQuestions){
                responseDtos.add(multipleChoiceQuestionToResponseMapper(multipleChoiceQuestion));
            }
            return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public MultipleChoiceResponseDto multipleChoiceQuestionToResponseMapper(MultipleChoiceQuestion multipleChoiceQuestion){
        MultipleChoiceResponseDto multipleChoiceResponseDto=new MultipleChoiceResponseDto();
        multipleChoiceResponseDto.setInstructorName(multipleChoiceQuestion.getCreatedBy().getName());
        multipleChoiceResponseDto.setQuestionId(multipleChoiceQuestion.getId());
        multipleChoiceResponseDto.setDescription(multipleChoiceQuestion.getDescription());
        multipleChoiceResponseDto.setPoints(multipleChoiceQuestion.getPoints());
        multipleChoiceResponseDto.setCorrectAnswer(multipleChoiceQuestion.getCorrectAnswer());
        List<OptionDto> optionDtos=new ArrayList<>();
        for(Option option:multipleChoiceQuestion.getOptions()){
            OptionDto optionDto=new OptionDto();
            optionDto.setValue(option.getValue());
            optionDtos.add(optionDto);
        }
        multipleChoiceResponseDto.setOptions(optionDtos);

        return multipleChoiceResponseDto;
    }
}
