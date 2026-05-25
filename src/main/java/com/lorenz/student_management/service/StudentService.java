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

    // method to retrieve all students information from the database
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // method to verify if a record exist in the database
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found " + id));
            // orElseThrow() - throw exception 
            // Runtime Exeption - errors that happens while system is running
    }

    // method to update exisisting student record
    public Student updateStudent(Long id, Student updated) {
        var student = getStudentById(id);
        student.setFirstName(updated.getFirstName());
        student.setLastName(updated.getLastName());
        student.setEmail(updated.getEmail());
        student.setCourse(updated.getCourse());
        student.setYearLevel(updated.getYearLevel());
        return studentRepository.save(student);
    }

    // method to delete student record
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}