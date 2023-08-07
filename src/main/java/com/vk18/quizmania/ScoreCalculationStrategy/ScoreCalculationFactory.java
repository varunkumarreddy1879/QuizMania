package com.vk18.quizmania.ScoreCalculationStrategy;

import com.vk18.quizmania.model.FillBlankQuestion;
import com.vk18.quizmania.model.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreCalculationFactory {
    
    static MultipleChoiceQuestionScoreCalculator multipleChoiceQuestionScoreCalculator;
    static TrueFalseQuestionScoreCalculator trueFalseQuestionScoreCalculator;
    static FillBlankQuestionScoreCalculator fillBlankQuestionScoreCalculator;
    
    @Autowired
    public ScoreCalculationFactory(MultipleChoiceQuestionScoreCalculator multipleChoiceQuestionScoreCalculator,
                                   TrueFalseQuestionScoreCalculator trueFalseQuestionScoreCalculator,
                                   FillBlankQuestionScoreCalculator fillBlankQuestionScoreCalculator){
        this.multipleChoiceQuestionScoreCalculator=multipleChoiceQuestionScoreCalculator;
        this.fillBlankQuestionScoreCalculator=fillBlankQuestionScoreCalculator;
        this.trueFalseQuestionScoreCalculator=trueFalseQuestionScoreCalculator;
    }
    public static ScoreCalculator calculateScoreFactory(QuestionType questionType){
        if(questionType==QuestionType.Multiple_Choice){
            return multipleChoiceQuestionScoreCalculator;
        }

        if(questionType==QuestionType.True_False){
            return trueFalseQuestionScoreCalculator;
        }
        
        if(questionType==QuestionType.Fill_Blank){
            return fillBlankQuestionScoreCalculator;
        }
        
        return null;
    }
}
