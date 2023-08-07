package com.vk18.quizmania.service;

import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.*;
import com.vk18.quizmania.repository.InstructorRepository;
import com.vk18.quizmania.repository.TrueFalseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrueFalseQuestionService {
    TrueFalseQuestionRepository trueFalseQuestionRepository;
    InstructorRepository instructorRepository;

    @Autowired
    public TrueFalseQuestionService(InstructorRepository instructorRepository,TrueFalseQuestionRepository trueFalseQuestionRepository){
        this.trueFalseQuestionRepository=trueFalseQuestionRepository;
        this.instructorRepository=instructorRepository;
    }

    public TrueFalseQuestion add(Long instructorId, String description, DifficultyLevel difficultyLevel, boolean correctAnswer, int points, QuestionType questionType) throws InvalidArgumentException {
        /*
        check instructor exist or not
        create question , save and return
         */

        Optional<Instructor> optionalInstructor=instructorRepository.findById(instructorId);
        if(optionalInstructor.isEmpty()){
            throw new InvalidArgumentException("Instructor id : "+instructorId+" is invalid.");
        }

        TrueFalseQuestion question=new TrueFalseQuestion();
        question.setDescription(description);
        question.setDifficultyLevel(difficultyLevel);
        question.setPoints(points);
        question.setCorrectAnswer(correctAnswer);
        question.setCreatedBy(optionalInstructor.get());
        question.setType(questionType);

        return trueFalseQuestionRepository.save(question);

    }

    public TrueFalseQuestion update(Long questionId, Long instructorId, String description, DifficultyLevel difficultyLevel, boolean correctAnswer, int points) throws InvalidArgumentException {
        /*
        check question exist or not
        check question is created by user or not
        update question, save and return
         */

        Optional<TrueFalseQuestion> optionalQuestion=trueFalseQuestionRepository.findById(questionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        TrueFalseQuestion question=optionalQuestion.get();
        if(!question.getCreatedBy().getId().equals(instructorId)){
            throw new InvalidArgumentException("Only created instructor can update a question.");
        }

        question.setDescription(description);
        question.setDifficultyLevel(difficultyLevel);
        question.setPoints(points);
        question.setCorrectAnswer(correctAnswer);

        return trueFalseQuestionRepository.save(question);
    }

    public TrueFalseQuestion delete(Long questionId) throws InvalidArgumentException {
        /*
        check question exist
        delete and return question
         */

        Optional<TrueFalseQuestion> optionalQuestion=trueFalseQuestionRepository.findById(questionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        TrueFalseQuestion question=optionalQuestion.get();
        trueFalseQuestionRepository.delete(question);
        return question;
    }

    public TrueFalseQuestion getQuestionById(Long questionId) throws InvalidArgumentException {
        Optional<TrueFalseQuestion> optionalQuestion=trueFalseQuestionRepository.findById(questionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        return optionalQuestion.get();
    }

    public List<TrueFalseQuestion> getAllQuestions(Long instructorId) throws InvalidArgumentException {
        Optional<Instructor> optionalInstructor=instructorRepository.findById(instructorId);
        if(optionalInstructor.isEmpty()){
            throw new InvalidArgumentException("Instructor id : "+instructorId+" is invalid.");
        }
        return trueFalseQuestionRepository.findAllByCreatedBy(optionalInstructor.get());
    }


}
