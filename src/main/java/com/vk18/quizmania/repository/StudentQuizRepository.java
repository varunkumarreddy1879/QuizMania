package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.StudentQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentQuizRepository extends JpaRepository<StudentQuiz,Long> {
    public StudentQuiz save(StudentQuiz studentQuiz);
}
