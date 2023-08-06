package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion,Long> {

    public MultipleChoiceQuestion save(MultipleChoiceQuestion question);

    public void delete(MultipleChoiceQuestion question);

    public Optional<MultipleChoiceQuestion> findById(Long questionId);
}
