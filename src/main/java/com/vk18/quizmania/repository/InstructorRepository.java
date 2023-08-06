package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    public Instructor save(Instructor instructor);
    public Optional<Instructor> findByPhone(String phone);

    public Optional<Instructor> findById(Long id);
    public void delete(Instructor instructor);
    public List<Instructor> findAll();

}
