package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.*;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Question;
import com.vk18.quizmania.model.Quiz;
import com.vk18.quizmania.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    QuizService quizService;
    QuestionController questionController;

    @Autowired
    public QuizController(QuizService quizService,QuestionController questionController){
        this.questionController=questionController;
        this.quizService=quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<QuizResponseDto> create(@RequestBody CreateQuizRequestDto requestDto){
        Long instructorId=requestDto.getInstructorId();
        String category=requestDto.getCategory();
        String quizDescription=requestDto.getQuizDescription();

        try{
            Quiz quiz=quizService.create(instructorId,category,quizDescription);
            return ResponseEntity.status(HttpStatus.OK).body(quizToResponseDto(quiz));
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
    public ResponseEntity<QuizResponseDto> update(@RequestBody UpdateQuizRequestDto requestDto){
        Long quizId=requestDto.getQuizId();
        Long instructorId=requestDto.getInstructorId();
        String category=requestDto.getCategory();
        String quizDescription=requestDto.getQuizDescription();

        try{
            Quiz quiz=quizService.update(quizId,instructorId,category,quizDescription);
            return ResponseEntity.status(HttpStatus.OK).body(quizToResponseDto(quiz));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<QuizResponseDto> get(@PathVariable Long quizId){

        try{
            Quiz quiz=quizService.get(quizId);
            return ResponseEntity.status(HttpStatus.OK).body(quizToResponseDto(quiz));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<QuizResponseDto> delete(@RequestBody DeleteQuizRequestDto requestDto){
        Long instructorId=requestDto.getInstructorId();
        Long quizId= requestDto.getQuizId();

        try{
            Quiz quiz=quizService.delete(instructorId,quizId);
            return ResponseEntity.status(HttpStatus.OK).body(quizToResponseDto(quiz));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/addQuestionsList")
    public ResponseEntity<List<QuestionResponseDto>> AddQuestionsList(@RequestBody QuestionListRequestDto requestDto){
        Long quizId= requestDto.getQuizId();
        Long instructorId=requestDto.getInstructorId();
        List<Long> questionIds=requestDto.getQuestionIds();

        try{
            List<Question> addedQuestions=quizService.addQuestionsList(quizId,instructorId,questionIds);
            List<QuestionResponseDto> addedQuestionDtos=new ArrayList<>();
            for(Question question:addedQuestions){
                addedQuestionDtos.add(questionController.questionToResponseMapper(question));
            }
            return ResponseEntity.status(HttpStatus.OK).body(addedQuestionDtos);
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/removeQuestionsList")
    public ResponseEntity<List<QuestionResponseDto>> RemoveQuestionsList(@RequestBody QuestionListRequestDto requestDto){
        Long quizId= requestDto.getQuizId();
        Long instructorId=requestDto.getInstructorId();
        List<Long> questionIds=requestDto.getQuestionIds();

        try{
            List<Question> removedQuestions=quizService.removeQuestionsList(quizId,instructorId,questionIds);
            List<QuestionResponseDto> removedQuestionDtos=new ArrayList<>();
            for(Question question:removedQuestions){
                removedQuestionDtos.add(questionController.questionToResponseMapper(question));
            }
            return ResponseEntity.status(HttpStatus.OK).body(removedQuestionDtos);
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    public QuizResponseDto quizToResponseDto(Quiz quiz){
        QuizResponseDto quizResponseDto=new QuizResponseDto();
        quizResponseDto.setQuizId(quiz.getId());
        quizResponseDto.setQuizDescription(quiz.getQuizDescription());
        quizResponseDto.setCategory(quiz.getCategory());
        quizResponseDto.setInstructorName(quiz.getInstructor().getName());
        List<Long> questionIds=new ArrayList<>();
        for(Question question: quiz.getQuestions()){
            questionIds.add(question.getId());
        }
        quizResponseDto.setQuestionIds(questionIds);

        return quizResponseDto;
    }
}
