package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.AddStudentRequestDto;
import com.vk18.quizmania.dtos.StudentResponseDto;
import com.vk18.quizmania.dtos.UpdateStudentRequestDto;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Student;
import com.vk18.quizmania.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<StudentResponseDto> addStudent(@RequestBody AddStudentRequestDto requestDto){
        String name=requestDto.getName();
        String phone=requestDto.getPhone();
        String password= requestDto.getPassword();

        try{
            Student student=studentService.add(name,phone,password);
            return ResponseEntity.status(HttpStatus.OK).body(studentToStudentResponseDtoMapper(student));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/update")
    public ResponseEntity<StudentResponseDto> update(@RequestBody UpdateStudentRequestDto requestDto){
        Long studentId=requestDto.getStudentId();
        String name=requestDto.getName();
        String phone=requestDto.getPhone();
        String password= requestDto.getPassword();

        try{
            Student student=studentService.update(studentId,name,phone,password);
            return ResponseEntity.status(HttpStatus.OK).body(studentToStudentResponseDtoMapper(student));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<StudentResponseDto> deleteStudent(@PathVariable Long studentId){

        try{
            Student student=studentService.delete(studentId);
            return ResponseEntity.status(HttpStatus.OK).body(studentToStudentResponseDtoMapper(student));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get/{studentId}")
    public ResponseEntity<StudentResponseDto> get(@PathVariable Long studentId){

        try{
            Student student=studentService.get(studentId);
            return ResponseEntity.status(HttpStatus.OK).body(studentToStudentResponseDtoMapper(student));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentResponseDto>> getAll(){

        try{
            List<Student> students=studentService.getAll();
            List<StudentResponseDto> studentDtos=new ArrayList<>();
            for(Student student:students){
                studentDtos.add(studentToStudentResponseDtoMapper(student));
            }
            return ResponseEntity.status(HttpStatus.OK).body(studentDtos);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public StudentResponseDto studentToStudentResponseDtoMapper(Student student){
        StudentResponseDto studentResponseDto=new StudentResponseDto();
        studentResponseDto.setStudentId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setPhone(student.getPhone());

        return studentResponseDto;
    }

}
