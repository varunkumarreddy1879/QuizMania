package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.Option;
import com.vk18.quizmania.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    public Optional<Question> findById(Long questionId);
    public Question save(Question question);
    public List<Question> findAllByCreatedBy(Instructor instructor);
}
