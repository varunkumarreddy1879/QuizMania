package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.Student;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    public Student save(Student student);
    public Optional<Student> findByPhone(String phone);

    public Optional<Student> findById(Long id);
    public void delete(Student student);
    public List<Student> findAll();
}
