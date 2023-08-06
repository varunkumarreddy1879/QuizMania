package com.vk18.quizmania.service;

import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.*;
import com.vk18.quizmania.repository.InstructorRepository;
import com.vk18.quizmania.repository.MultipleChoiceQuestionRepository;
import org.apache.catalina.User;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultipleChoiceQuestionService {
    MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    InstructorRepository instructorRepository;

    @Autowired
    public MultipleChoiceQuestionService(InstructorRepository instructorRepository,MultipleChoiceQuestionRepository multipleChoiceQuestionRepository){
        this.multipleChoiceQuestionRepository=multipleChoiceQuestionRepository;
        this.instructorRepository=instructorRepository;
    }

    public MultipleChoiceQuestion add(Long instructorId, String description, DifficultyLevel difficultyLevel, String correctAnswer, int points, List<Option> options) throws InvalidArgumentException {
        /*
        check instructor exist or not
        create question , save and return
         */

        Optional<Instructor> optionalInstructor=instructorRepository.findById(instructorId);
        if(optionalInstructor.isEmpty()){
            throw new InvalidArgumentException("Instructor id : "+instructorId+" is invalid.");
        }

        MultipleChoiceQuestion question=new MultipleChoiceQuestion();
        question.setDescription(description);
        question.setDifficultyLevel(difficultyLevel);
        question.setOptions(options);
        question.setPoints(points);
        question.setCorrectAnswer(correctAnswer);
        question.setCreatedBy(optionalInstructor.get());

        return multipleChoiceQuestionRepository.save(question);

    }

    public MultipleChoiceQuestion update(Long questionId, Long instructorId, String description, DifficultyLevel difficultyLevel, String correctAnswer, int points, List<Option> options) throws InvalidArgumentException {
        /*
        check question exist or not
        check question is created by user or not
        update question, save and return
         */

        Optional<MultipleChoiceQuestion> optionalQuestion=multipleChoiceQuestionRepository.findById(questionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        MultipleChoiceQuestion question=optionalQuestion.get();
        if(!question.getCreatedBy().getId().equals(instructorId)){
            throw new InvalidArgumentException("Only created instructor can update a question.");
        }

        question.setDescription(description);
        question.setDifficultyLevel(difficultyLevel);
        question.setPoints(points);
        question.setOptions(options);
        question.setCorrectAnswer(correctAnswer);

        return multipleChoiceQuestionRepository.save(question);
    }

    public MultipleChoiceQuestion delete(Long questionId) throws InvalidArgumentException {
        /*
        check question exist
        delete and return question
         */

        Optional<MultipleChoiceQuestion> optionalQuestion=multipleChoiceQuestionRepository.findById(questionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        MultipleChoiceQuestion question=optionalQuestion.get();
        multipleChoiceQuestionRepository.delete(question);
        return question;
    }

    public MultipleChoiceQuestion getQuestionById(Long questionId) throws InvalidArgumentException {
        Optional<MultipleChoiceQuestion> optionalQuestion=multipleChoiceQuestionRepository.findById(questionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        return optionalQuestion.get();
    }

    public List<MultipleChoiceQuestion> getAllQuestions() {
        return multipleChoiceQuestionRepository.findAll();
    }
}
