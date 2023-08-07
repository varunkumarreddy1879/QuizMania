package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.FillBlankQuestion;
import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.TrueFalseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FillBlankQuestionRepository extends JpaRepository<FillBlankQuestion,Long> {
    public FillBlankQuestion save(FillBlankQuestion question);

    public void delete(FillBlankQuestion question);
    public Optional<FillBlankQuestion> findById(Long questionId);

    public List<FillBlankQuestion> findAllByCreatedBy(Instructor instructor);
}
