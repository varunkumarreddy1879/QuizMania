package com.vk18.quizmania.controller;

import com.vk18.quizmania.dtos.LoginRequestDto;
import com.vk18.quizmania.dtos.SignUpRequestDto;
import com.vk18.quizmania.dtos.UpdatePasswordRequestDto;
import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto requestDto){
        String name=requestDto.getName();
        String phone= requestDto.getPhone();
        String password= requestDto.getPassword();

        try{
            userService.signUp(name,phone,password);
            return ResponseEntity.status(HttpStatus.OK).body("Registered successfully.");
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user.");
        }

    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto){
        String phone=requestDto.getPhone();
        String password= requestDto.getPassword();

        try{
            userService.login(phone,password);
            return ResponseEntity.status(HttpStatus.OK).body("User loged in  successfully.");
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while login.");
        }

    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequestDto requestDto){
        String phone=requestDto.getPhone();
        String oldPassword=requestDto.getOldPassword();
        String newPassword=requestDto.getNewPassword();

        try{
            userService.updatePassword(phone,oldPassword,newPassword);
            return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully");
        }
        catch (InvalidArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating password.");
        }

        return null;
    }
}
