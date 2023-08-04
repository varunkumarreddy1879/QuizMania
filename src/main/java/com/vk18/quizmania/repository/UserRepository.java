package com.vk18.quizmania.repository;

import com.vk18.quizmania.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findById (Long userId);

    Optional<User> findByPhone(String phone);
}
