package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.Question;
import com.vk18.quizmania.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    public Quiz save(Quiz quiz);

    Optional<Quiz> findByIdAndQuestions(Long quizId, Question question);

    public void delete(Quiz quiz);

    public void deleteByIdAndQuestions(Long quizId, Question question);
}
