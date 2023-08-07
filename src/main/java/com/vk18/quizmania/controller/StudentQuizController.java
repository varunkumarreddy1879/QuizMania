package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.QuestionAnswer;
import com.vk18.quizmania.dtos.SubmitQuizRequestDto;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Question;
import com.vk18.quizmania.service.StudentQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student-quiz")
public class StudentQuizController {
    StudentQuizService studentQuizService;

    @Autowired
    public StudentQuizController(StudentQuizService studentQuizService){
        this.studentQuizService=studentQuizService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody SubmitQuizRequestDto requestDto){
        Long quizId= requestDto.getQuizId();
        Long studentId=requestDto.getStudentId();
        List<QuestionAnswer> responses=requestDto.getResponses();

        try{
            int score=studentQuizService.submitQuiz(quizId,studentId,responses);
            return ResponseEntity.status(HttpStatus.OK).body(score);
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
