package com.example.springboot.student.service;

import com.example.springboot.student.entity.Student;
import com.example.springboot.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        log.info("getStudents was called");
       return studentRepository.findAll();
    }


    public void create(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean studentExists = studentRepository.existsById(id);

        if(!studentExists) {
            throw new IllegalStateException("student with id " + id + " was not found");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student studentById = studentRepository.findById(id).orElseThrow(()->
             new IllegalStateException("student with id " + id + " was not found")
        );

        if(name != null && name.length() > 0 && !Objects.equals(studentById.getName(), name)) {
            studentById.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(studentById.getEmail(), email)) {
            Optional<Student> student = studentRepository.findStudentByEmail(email);

            if(student.isPresent()) {
                throw new IllegalStateException("email taken");
            }

            studentById.setEmail(email);
        }

    }
}

