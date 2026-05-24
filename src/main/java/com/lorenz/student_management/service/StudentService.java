package com.lorenz.student_management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.lorenz.student_management.model.Student;
import com.lorenz.student_management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;








@Service // it is where you put rules/validation, processing of data
@RequiredArgsConstructor 
// automatically generate a constructor for the fields that are required
// required fields: final, fields with @NonNull
// it helps to inject dependencies automatically
public class StudentService {
    
    private final StudentRepository studentRepository;

    // method to create student record
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}