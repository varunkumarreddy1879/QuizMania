package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    public Question save(Question question);
    public void delete(Question question);
}
