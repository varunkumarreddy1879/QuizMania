package com.vk18.quizmania.service;

import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.Student;
import com.vk18.quizmania.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository){
        this.instructorRepository=instructorRepository;
    }

    public Instructor add(String name, String phone, String password) throws InvalidArgumentException {
        /*
        check phone already exist
        create instructor, save and return
         */

        Optional<Instructor> optionalInstructor=instructorRepository.findByPhone(phone);
        if(optionalInstructor.isPresent()){
            throw new InvalidArgumentException("Instructor already exist with phone : "+phone);
        }

        Instructor instructor=new Instructor();
        instructor.setName(name);
        instructor.setPhone(phone);
        instructor.setPassword(password);

        return instructorRepository.save(instructor);
    }

    public Instructor update(Long instructorId, String name, String phone, String password) throws InvalidArgumentException {
        /*
        check instructor exist or not
        update, save and return student
         */

        Optional<Instructor> optionalInstructor=instructorRepository.findById(instructorId);
        if(optionalInstructor.isEmpty()){
            throw new InvalidArgumentException("Instructor does not exist with id:"+instructorId+".");
        }

        Instructor instructor=optionalInstructor.get();
        instructor.setName(name);
        instructor.setPhone(phone);
        instructor.setPassword(password);

        return instructorRepository.save(instructor);
    }

    public Instructor delete(Long instructorId) throws InvalidArgumentException {
        /*
        check instructor exist
        delete and return
         */

        Optional<Instructor> optionalInstructor=instructorRepository.findById(instructorId);
        if(optionalInstructor.isEmpty()){
            throw new InvalidArgumentException("Instructor does not exist with id:"+instructorId+".");
        }

        Instructor instructor=optionalInstructor.get();
        instructorRepository.delete(instructor);
        return instructor;
    }

    public Instructor get(Long instructorId) throws InvalidArgumentException {
        Optional<Instructor> optionalInstructor=instructorRepository.findById(instructorId);
        if(optionalInstructor.isEmpty()){
            throw new InvalidArgumentException("Instructor does not exist with id:"+instructorId+".");
        }

        return optionalInstructor.get();
    }

    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }
}
