package com.vk18.quizmania.ScoreCalculationStrategy;

import com.vk18.quizmania.model.TrueFalseQuestion;
import com.vk18.quizmania.repository.TrueFalseQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrueFalseQuestionScoreCalculator implements ScoreCalculator{
    TrueFalseQuestionRepository trueFalseQuestionRepository;

    @Autowired
    public TrueFalseQuestionScoreCalculator(TrueFalseQuestionRepository trueFalseQuestionRepository){
        this.trueFalseQuestionRepository=trueFalseQuestionRepository;
    }

    @Override
    public int calculateScore(Long questionId, String submittedAnswer) {
        TrueFalseQuestion question=trueFalseQuestionRepository.findById(questionId).get();
        if((question.isCorrectAnswer()&&submittedAnswer.equalsIgnoreCase("True"))||
                        ((!question.isCorrectAnswer())&&(!submittedAnswer.equalsIgnoreCase("True")))
                ){
                    return question.getPoints();
        }
        return 0;
    }
}
