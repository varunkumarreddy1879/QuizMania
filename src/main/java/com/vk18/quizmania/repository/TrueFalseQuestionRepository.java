package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.MultipleChoiceQuestion;
import com.vk18.quizmania.model.TrueFalseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrueFalseQuestionRepository extends JpaRepository<TrueFalseQuestion,Long> {
    public TrueFalseQuestion save(TrueFalseQuestion question);

    public void delete(TrueFalseQuestion question);
    public Optional<TrueFalseQuestion> findById(Long questionId);

    public List<TrueFalseQuestion> findAllByCreatedBy(Instructor instructor);
}
