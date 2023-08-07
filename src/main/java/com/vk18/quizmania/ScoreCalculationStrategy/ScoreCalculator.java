package com.vk18.quizmania.ScoreCalculationStrategy;

public interface ScoreCalculator {
    public int calculateScore(Long questionId,String submittedAnswer);
}
