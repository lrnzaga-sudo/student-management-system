package com.lorenz.student_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lorenz.student_management.dto.request_dto.StudentRequestDto;
import com.lorenz.student_management.dto.response_dto.StudentResponseDto;
import com.lorenz.student_management.exception.DuplicateEmailException;
import com.lorenz.student_management.exception.StudentNotFoundException;
import com.lorenz.student_management.mapper.StudentMapper;
import com.lorenz.student_management.model.Student;
import com.lorenz.student_management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;








@Service 
@RequiredArgsConstructor
public class StudentService {
    
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    // method to create student record
    public StudentResponseDto createStudent(StudentRequestDto dto) {
        if (studentRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateEmailException(dto.getEmail());
        }

        var student = studentMapper.toEntity(dto);
        var saved = studentRepository.save(student);
        return studentMapper.toResponseDto(saved);
    }

    // method to retrieve all students information from the database
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // method to verify if a record exist in the database
    public StudentResponseDto getStudentById(Long id) {
        var student = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.toResponseDto(student);
    }

    // method to update exisisting student record
    // public Student updateStudent(Long id, Student updated) {
    //     var student = getStudentById(id);
    //     student.setFirstName(updated.getFirstName());
    //     student.setLastName(updated.getLastName());
    //     student.setEmail(updated.getEmail());
    //     student.setCourse(updated.getCourse());
    //     student.setYearLevel(updated.getYearLevel());
    //     return studentRepository.save(student);
    // }

    // method to delete student record
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}