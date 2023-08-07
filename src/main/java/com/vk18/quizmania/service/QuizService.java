package com.vk18.quizmania.service;

import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.Question;
import com.vk18.quizmania.model.Quiz;
import com.vk18.quizmania.repository.InstructorRepository;
import com.vk18.quizmania.repository.QuestionRepository;
import com.vk18.quizmania.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    QuizRepository quizRepository;
    InstructorRepository instructorRepository;
    QuestionRepository questionRepository;

    @Autowired
    public QuizService(QuestionRepository questionRepository,QuizRepository quizRepository,InstructorRepository instructorRepository){
        this.quizRepository=quizRepository;
        this.questionRepository=questionRepository;
        this.instructorRepository=instructorRepository;
    }

    public Quiz create(Long instructorId, String category, String quizDescription) throws InvalidArgumentException {
        /*
        check instructor exist
        create quiz , save and return
         */

        Optional<Instructor> optionalInstructor=instructorRepository.findById(instructorId);
        if(optionalInstructor.isEmpty()){
            throw new InvalidArgumentException("Instrucor id : "+instructorId+ " is invalid.");
        }

        Instructor instructor=optionalInstructor.get();
        Quiz quiz=new Quiz();
        quiz.setQuizDescription(quizDescription);
        quiz.setCategory(category);
        quiz.setInstructor(instructor);

        return quizRepository.save(quiz);
    }

    public List<Question> addQuestionsList(Long quizId, Long instructorId, List<Long> questionIds) throws InvalidArgumentException {
        /*
        check quiz created user matching or not
        check question already in quiz
        add valid questions to quiz
        save quiz
        return list of valid added questions
         */

        Optional<Quiz> optionalQuiz=quizRepository.findById(quizId);
        if(optionalQuiz.isEmpty()){
            throw new InvalidArgumentException("Quiz id : "+ quizId + " is invalid.");
        }

        Quiz quiz=optionalQuiz.get();

        if(!quiz.getInstructor().getId().equals(instructorId)){
            throw new InvalidArgumentException("Only created instructor can add questions to quiz.");
        }

        List<Question> addedQuestion=new ArrayList<>();

        for(Long questionId: questionIds){
            Optional<Question> optionalQuestion=questionRepository.findById(questionId);
            if(optionalQuestion.isPresent()){
                Question question=optionalQuestion.get();
                Optional<Quiz> optionalQuizQuestion=quizRepository.findByIdAndQuestions(quizId,question);

                if(optionalQuizQuestion.isEmpty()){
                    quiz.getQuestions().add(question);
                    quizRepository.save(quiz);
                    addedQuestion.add(question);
                }
            }
        }

        return addedQuestion;
    }

    public Quiz delete(Long instructorId, Long quizId) throws InvalidArgumentException {
        /*
        check quiz exist
        check quiz instructor matching or not
        delete and return quiz
         */
        Optional<Quiz> optionalQuiz=quizRepository.findById(quizId);
        if(optionalQuiz.isEmpty()){
            throw new InvalidArgumentException("Quiz id : "+ quizId + " is invalid.");
        }

        Quiz quiz=optionalQuiz.get();
        if(!quiz.getInstructor().getId().equals(instructorId)){
            throw new InvalidArgumentException("Only created instructor can delete quiz.");
        }

        quizRepository.delete(quiz);
        return quiz;
    }

    public Quiz update(Long quizId, Long instructorId, String category, String quizDescription) throws InvalidArgumentException {
        /*
        check quiz exist
        check instructor matching
        update , save and return quiz
         */
        Optional<Quiz> optionalQuiz=quizRepository.findById(quizId);
        if(optionalQuiz.isEmpty()){
            throw new InvalidArgumentException("Quiz id : "+ quizId + " is invalid.");
        }

        Quiz quiz=optionalQuiz.get();
        if(!quiz.getInstructor().getId().equals(instructorId)){
            throw new InvalidArgumentException("Only created instructor can delete quiz.");
        }

        quiz.setQuizDescription(quizDescription);
        quiz.setCategory(category);

        return quizRepository.save(quiz);

    }

    public List<Question> removeQuestionsList(Long quizId, Long instructorId, List<Long> questionIds) throws InvalidArgumentException {
        /*
        check quiz created user matching or not
        check question exist in quiz
        remove questions from quiz
        save quiz
        return list of valid removed questions
         */

        Optional<Quiz> optionalQuiz=quizRepository.findById(quizId);
        if(optionalQuiz.isEmpty()){
            throw new InvalidArgumentException("Quiz id : "+ quizId + " is invalid.");
        }

        Quiz quiz=optionalQuiz.get();

        if(!quiz.getInstructor().getId().equals(instructorId)){
            throw new InvalidArgumentException("Only created instructor can add questions to quiz.");
        }

        List<Question> removedQuestions=new ArrayList<>();

        for(Long questionId: questionIds){
            Optional<Question> optionalQuestion=questionRepository.findById(questionId);
            if(optionalQuestion.isPresent()){
                Question question=optionalQuestion.get();
                Optional<Quiz> optionalQuizQuestion=quizRepository.findByIdAndQuestions(quizId,question);

                if(optionalQuizQuestion.isPresent()){
                    quiz.getQuestions().add(question);
                    quizRepository.deleteByIdAndQuestions(quizId,question);
                    removedQuestions.add(question);
                }
            }
        }

        return removedQuestions;
    }
}
