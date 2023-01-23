package com.example.springboot.student;

import com.example.springboot.student.entity.Student;
import com.example.springboot.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student mike = new Student(
                    "Mike Tyson",
                    LocalDate.of(1966, Month.JUNE, 30),
                    "mike.tyson@gmail.com");
            studentRepository.save(mike);
        };

    }
}
