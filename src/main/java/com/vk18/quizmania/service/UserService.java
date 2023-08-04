package com.vk18.quizmania.service;

import com.vk18.quizmania.exception.InvalidArgumentException;
import com.vk18.quizmania.model.User;
import com.vk18.quizmania.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public void signUp(String name, String phone, String password) throws InvalidArgumentException {
        /*
        check user alredy exist with phone
        if exist throw exception
        create user and save
         */

        Optional<User> optionalUser=userRepository.findByPhone(phone);

        if(optionalUser.isPresent()){
            throw new InvalidArgumentException("User with phone : "+phone+" already exist.");
        }

        User user=new User();
        user.setName(name);
        user.setPhone(phone);
//        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
//        user.setPassword(encoder.encode(password));
        user.setPassword((password));

        userRepository.save(user);

    }

    public void login(String phone, String password) throws InvalidArgumentException {
        Optional<User> optionalUser=userRepository.findByPhone(phone);

        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("No user exist with phone : "+phone);
        }
//        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
//        if(!encoder.matches(password,optionalUser.get().getPassword())){
//            throw new InvalidArgumentException("password is incorrect.");
//        }

        if(!optionalUser.get().getPassword().equals(password)){
            throw new InvalidArgumentException("Password is incorrect.");
        }
    }

    public void updatePassword(String phone, String oldPassword, String newPassword) throws InvalidArgumentException {
        /*
        check user exist
        check password matching
        update password and save
         */

        Optional<User> optionalUser=userRepository.findByPhone(phone);

        if(optionalUser.isEmpty()){
            throw new InvalidArgumentException("No user exist with phone : "+phone);
        }

        User user=optionalUser.get();

        if(!user.getPassword().equals(oldPassword)){
            throw new InvalidArgumentException("Incorrect password.");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
