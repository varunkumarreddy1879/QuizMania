package com.vk18.quizmania.service;

import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Option;
import com.vk18.quizmania.model.Student;
import com.vk18.quizmania.repository.StudentRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public Student add(String name, String phone, String password) throws InvalidArgumentException {
        /*
        check phone already exist
        create student, save and return
         */

        Optional<Student> optionalStudent=studentRepository.findByPhone(phone);
        if(optionalStudent.isPresent()){
            throw new InvalidArgumentException("Student already exist with phone : "+phone);
        }

        Student student=new Student();
        student.setName(name);
        student.setPhone(phone);
        student.setPassword(password);

        return studentRepository.save(student);
    }

    public Student update(Long studentId, String name, String phone, String password) throws InvalidArgumentException {
        /*
        check student exist or not
        update, save and return student
         */

        Optional<Student> optionalStudent=studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()){
            throw new InvalidArgumentException("Student does not exist with id:"+studentId+".");
        }

        Student student=optionalStudent.get();
        student.setName(name);
        student.setPhone(phone);
        student.setPassword(password);

        return studentRepository.save(student);
    }

    public Student delete(Long studentId) throws InvalidArgumentException {

        /*
        check student exist
        delete and student
         */

        Optional<Student> optionalStudent=studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()){
            throw new InvalidArgumentException("Student does not exist with id:"+studentId+".");
        }

        Student student=optionalStudent.get();
        studentRepository.delete(student);
        return student;
    }

    public Student get(Long studentId) throws InvalidArgumentException {
        Optional<Student> optionalStudent=studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()){
            throw new InvalidArgumentException("Student does not exist with id:"+studentId+".");
        }

        return optionalStudent.get();
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
