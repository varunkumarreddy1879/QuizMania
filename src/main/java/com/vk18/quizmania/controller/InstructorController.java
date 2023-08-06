package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.*;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.Instructor;
import com.vk18.quizmania.model.Student;
import com.vk18.quizmania.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    InstructorService  instructorService;
    @Autowired
    public InstructorController(InstructorService instructorService){
        this.instructorService=instructorService;
    }

    @PostMapping("/register")
    public ResponseEntity<InstructorResponseDto> addInstructor(@RequestBody AddInstructorRequestDto requestDto){
        String name=requestDto.getName();
        String phone=requestDto.getPhone();
        String password= requestDto.getPassword();

        try{
            Instructor instructor=instructorService.add(name,phone,password);
            return ResponseEntity.status(HttpStatus.OK).body(instructorToInstructorResponseDto(instructor));
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
    public ResponseEntity<InstructorResponseDto> update(@RequestBody UpdateInstructorRequestDto requestDto){
        Long instructorId=requestDto.getInstructorId();
        String name=requestDto.getName();
        String phone=requestDto.getPhone();
        String password= requestDto.getPassword();

        try{
            Instructor instructor=instructorService.update(instructorId,name,phone,password);
            return ResponseEntity.status(HttpStatus.OK).body(instructorToInstructorResponseDto(instructor));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{instructorId}")
    public ResponseEntity<InstructorResponseDto> deleteInstructor(@PathVariable Long instructorId){

        try{
            Instructor instructor=instructorService.delete(instructorId);
            return ResponseEntity.status(HttpStatus.OK).body(instructorToInstructorResponseDto(instructor));
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get/{instructorId}")
    public ResponseEntity<InstructorResponseDto> get(@PathVariable Long instructorId){

        try{
            Instructor instructor=instructorService.get(instructorId);
            return ResponseEntity.status(HttpStatus.OK).body(instructorToInstructorResponseDto(instructor));
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
    public ResponseEntity<List<InstructorResponseDto>> getAll(){

        try{
            List<Instructor> instructors=instructorService.getAll();
            List<InstructorResponseDto> instructorResponseDtos=new ArrayList<>();
            for(Instructor instructor:instructors){
                instructorResponseDtos.add(instructorToInstructorResponseDto(instructor));
            }
            return ResponseEntity.status(HttpStatus.OK).body(instructorResponseDtos);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public InstructorResponseDto instructorToInstructorResponseDto(Instructor instructor){
        InstructorResponseDto instructorResponseDto=new InstructorResponseDto();
        instructorResponseDto.setInstructorId(instructor.getId());
        instructorResponseDto.setName(instructor.getName());
        instructorResponseDto.setPhone(instructor.getPhone());

        return instructorResponseDto;
    }

}
