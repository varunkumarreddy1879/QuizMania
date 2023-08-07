package com.vk18.quizmania.service;

import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.Question;
import com.vk18.quizmania.repository.InstructorRepository;
import com.vk18.quizmania.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    QuestionRepository questionRepository;
    InstructorRepository instructorRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository,InstructorRepository instructorRepository){
        this.questionRepository=questionRepository;
        this.instructorRepository=instructorRepository;
    }

    public Question getQuestion(Long quesionId) throws InvalidArgumentException {
        Optional<Question> optionalQuestion=questionRepository.findById(quesionId);
        if(optionalQuestion.isEmpty()){
            throw new InvalidArgumentException("Question id : "+quesionId+" is invalid.");
        }

        return questionRepository.save(optionalQuestion.get());
    }

    public List<Question> getAllQuestions(Long instructorId) throws InvalidArgumentException {
        Optional<Instructor> optionalInstructor=instructorRepository.findById(instructorId);
        if(optionalInstructor.isEmpty()){
            throw new InvalidArgumentException("Instructor id : "+instructorId+" is invalid.");
        }

        return questionRepository.findAllByCreatedBy(optionalInstructor.get());
    }
}
