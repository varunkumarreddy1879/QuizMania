package com.vk18.quizmania.service;

import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.*;
import com.vk18.quizmania.repository.QuestionRepository;
import com.vk18.quizmania.repository.UserRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    UserRepository userRepository;
    QuestionRepository questionRepository;

    public QuestionService(UserRepository userRepository,QuestionRepository questionRepository){
        this.userRepository=userRepository;
        this.questionRepository=questionRepository;
    }
    public Question addMultipleChoiceQuestion(Long userId, String question, List<Choice> choices, String answer, DifficultyLevel difficultyLevel) throws InvalidArgumentException {
        /*
        check user is valid
        if not throw exception
        create a question and save and return
         */
        Optional<User> optionalUser=userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("User with id : "+userId+" does not exist.");
        }

        User user=optionalUser.get();
        MultipleChoiceQuestion newQuestion=new MultipleChoiceQuestion();
        newQuestion.setQuestion(question);
        newQuestion.setAnswer(answer);
        newQuestion.setCreatedBy(user);
        newQuestion.setDifficultyLevel(difficultyLevel);
        newQuestion.setChoices(choices);

        return questionRepository.save(newQuestion);
    }

    public Question addTrueOrFalseQuestion(Long userId, String question, String answer, DifficultyLevel difficultyLevel) throws InvalidArgumentException {

        Optional<User> optionalUser=userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("User with id : "+userId+" does not exist.");
        }

        User user=optionalUser.get();
        TrueOrFalseQuestion newQuestion=new TrueOrFalseQuestion();
        newQuestion.setQuestion(question);
        newQuestion.setAnswer(answer);
        newQuestion.setCreatedBy(user);
        newQuestion.setDifficultyLevel(difficultyLevel);

        return questionRepository.save(newQuestion);
    }

    public Question deleteQuestion(Long userId, Long questionId) throws InvalidArgumentException {
        Optional<User> optionalUser=userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("User with id : "+userId+" does not exist.");
        }

        Optional<Question> optionalQuestion=questionRepository.findById(questionId);

        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("Question with id : "+questionId+" does not exist.");
        }

        Question question=optionalQuestion.get();
        User user = optionalUser.get();

        if(!user.getId().equals(question.getCreatedBy().getId())){
            throw new InvalidArgumentException("Only created user can delete Question.");
        }

        questionRepository.delete(question);

        return question;
    }
}
