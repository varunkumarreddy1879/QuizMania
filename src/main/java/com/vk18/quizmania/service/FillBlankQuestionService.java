package com.vk18.quizmania.service;

import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.DifficultyLevel;
import com.vk18.quizmania.model.FillBlankQuestion;
import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.TrueFalseQuestion;
import com.vk18.quizmania.repository.FillBlankQuestionRepository;
import com.vk18.quizmania.repository.InstructorRepository;
import com.vk18.quizmania.repository.TrueFalseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FillBlankQuestionService {
    FillBlankQuestionRepository fillBlankQuestionRepository;
    InstructorRepository instructorRepository;

    @Autowired
    public FillBlankQuestionService(InstructorRepository instructorRepository,FillBlankQuestionRepository fillBlankQuestionRepository){
        this.fillBlankQuestionRepository=fillBlankQuestionRepository;
        this.instructorRepository=instructorRepository;
    }

    public FillBlankQuestion add(Long instructorId, String description, DifficultyLevel difficultyLevel, String correctAnswer, int points) throws InvalidArgumentException {
        /*
        check instructor exist or not
        create question , save and return
         */

        Optional<Instructor> optionalInstructor=instructorRepository.findById(instructorId);
        if(optionalInstructor.isEmpty()){
            throw new InvalidArgumentException("Instructor id : "+instructorId+" is invalid.");
        }

        FillBlankQuestion question=new FillBlankQuestion();
        question.setDescription(description);
        question.setDifficultyLevel(difficultyLevel);
        question.setPoints(points);
        question.setCorrectAnswer(correctAnswer);
        question.setCreatedBy(optionalInstructor.get());

        return fillBlankQuestionRepository.save(question);

    }

    public FillBlankQuestion update(Long questionId, Long instructorId, String description, DifficultyLevel difficultyLevel, String correctAnswer, int points) throws InvalidArgumentException {
        /*
        check question exist or not
        check question is created by user or not
        update question, save and return
         */

        Optional<FillBlankQuestion> optionalQuestion=fillBlankQuestionRepository.findById(questionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        FillBlankQuestion question=optionalQuestion.get();
        if(!question.getCreatedBy().getId().equals(instructorId)){
            throw new InvalidArgumentException("Only created instructor can update a question.");
        }

        question.setDescription(description);
        question.setDifficultyLevel(difficultyLevel);
        question.setPoints(points);
        question.setCorrectAnswer(correctAnswer);

        return fillBlankQuestionRepository.save(question);
    }

    public FillBlankQuestion delete(Long questionId) throws InvalidArgumentException {
        /*
        check question exist
        delete and return question
         */

        Optional<FillBlankQuestion> optionalQuestion=fillBlankQuestionRepository.findById(questionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        FillBlankQuestion question=optionalQuestion.get();
        fillBlankQuestionRepository.delete(question);
        return question;
    }

    public FillBlankQuestion getQuestionById(Long questionId) throws InvalidArgumentException {
        Optional<FillBlankQuestion> optionalQuestion=fillBlankQuestionRepository.findById(questionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        return optionalQuestion.get();
    }

    public List<FillBlankQuestion> getAllQuestions() {
        return fillBlankQuestionRepository.findAll();
    }

}
