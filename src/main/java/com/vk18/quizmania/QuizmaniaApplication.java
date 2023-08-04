package com.vk18.quizmania;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class QuizmaniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizmaniaApplication.class, args);
	}

}
