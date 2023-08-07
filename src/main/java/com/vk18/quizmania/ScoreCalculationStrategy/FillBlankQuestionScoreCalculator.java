package com.vk18.quizmania.ScoreCalculationStrategy;

import com.vk18.quizmania.model.FillBlankQuestion;
import com.vk18.quizmania.repository.FillBlankQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FillBlankQuestionScoreCalculator implements ScoreCalculator{
    FillBlankQuestionRepository fillBlankQuestionRepository;

    @Autowired
    public FillBlankQuestionScoreCalculator(FillBlankQuestionRepository fillBlankQuestionRepository){
        this.fillBlankQuestionRepository=fillBlankQuestionRepository;
    }

    @Override
    public int calculateScore(Long questionId, String submittedAnswer) {
        FillBlankQuestion question=fillBlankQuestionRepository.findById(questionId).get();
        if(question.getCorrectAnswer().equals(submittedAnswer)){
            return question.getPoints();
        }
        return 0;
    }
}
