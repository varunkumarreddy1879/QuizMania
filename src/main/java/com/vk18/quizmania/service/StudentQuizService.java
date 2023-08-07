package com.vk18.quizmania.service;

import com.vk18.quizmania.ScoreCalculationStrategy.ScoreCalculationFactory;
import com.vk18.quizmania.ScoreCalculationStrategy.ScoreCalculator;
import com.vk18.quizmania.dtos.QuestionAnswer;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Question;
import com.vk18.quizmania.model.Quiz;
import com.vk18.quizmania.model.Student;
import com.vk18.quizmania.model.StudentQuiz;
import com.vk18.quizmania.repository.QuestionRepository;
import com.vk18.quizmania.repository.QuizRepository;
import com.vk18.quizmania.repository.StudentQuizRepository;
import com.vk18.quizmania.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentQuizService {
    StudentQuizRepository studentQuizRepository;
    QuizRepository quizRepository;
    StudentRepository studentRepository;
    QuestionRepository questionRepository;

    @Autowired
    public StudentQuizService(QuestionRepository questionRepository,QuizRepository quizRepository,StudentRepository studentRepository,StudentQuizRepository studentQuizRepository){
        this.studentQuizRepository=studentQuizRepository;
        this.questionRepository=questionRepository;
        this.quizRepository=quizRepository;
        this.studentRepository=studentRepository;
    }

    public int submitQuiz(Long quizId, Long studentId, List<QuestionAnswer> responses) throws InvalidArgumentException {
        /*
        check quiz exit
        check student exist
        calculate score
        create a reacord
        return score
         */

        Optional<Quiz> optionalQuiz=quizRepository.findById(quizId);
        if(optionalQuiz.isEmpty()){
            throw new InvalidArgumentException("Quiz id : "+quizId+" is invalid.");
        }

        Optional<Student> optionalStudent=studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()){
            throw new InvalidArgumentException("Student id : "+quizId+" is invalid.");
        }


        int score=calculateScore(responses);
        StudentQuiz studentQuiz=new StudentQuiz();
        studentQuiz.setQuiz(optionalQuiz.get());
        studentQuiz.setStudent(optionalStudent.get());
        studentQuiz.setScore(score);

        studentQuizRepository.save(studentQuiz);

        return score;
    }

    public int calculateScore(List<QuestionAnswer> responses) throws InvalidArgumentException {

        int score=0;

        for(QuestionAnswer response:responses){
            Optional<Question> optionalQuestion=questionRepository.findById(response.getQuestionId());
            if(optionalQuestion.isEmpty()){
                throw new InvalidArgumentException("Some question id's are invalid.");
            }
            Question question=optionalQuestion.get();

            ScoreCalculator scoreCalculator = ScoreCalculationFactory.calculateScoreFactory(question.getType());
            scoreCalculator.calculateScore(question.getId(), response.getSubmittedAnswer());
        }
        return score;
    }
}
