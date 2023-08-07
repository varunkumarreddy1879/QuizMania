package com.vk18.quizmania.ScoreCalculationStrategy;

import com.vk18.quizmania.model.MultipleChoiceQuestion;
import com.vk18.quizmania.repository.MultipleChoiceQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultipleChoiceQuestionScoreCalculator implements ScoreCalculator{
    MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    @Autowired
    public MultipleChoiceQuestionScoreCalculator(MultipleChoiceQuestionRepository multipleChoiceQuestionRepository){
        this.multipleChoiceQuestionRepository=multipleChoiceQuestionRepository;
    }

    @Override
    public int calculateScore(Long questionId, String submittedAnswer) {
        MultipleChoiceQuestion question=multipleChoiceQuestionRepository.findById(questionId).get();
        if(question.getCorrectAnswer().equals(submittedAnswer)){
            return question.getPoints();
        }
        return 0;
    }
}
