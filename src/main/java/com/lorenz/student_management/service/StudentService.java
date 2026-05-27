package com.lorenz.student_management.service;

import java.time.LocalDateTime;
import java.util.Collections;
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
    public List<StudentResponseDto> getAllStudents() {
        var students = studentRepository.findAllByDeletedAtIsNull()
        .stream()
        .map(studentMapper::toResponseDto)
        .toList();

        if (students.isEmpty()) {
            return Collections.emptyList();
        }

        return students;
    }


    // method to verify if a record exist in the database
    public StudentResponseDto getStudentById(Long id) {
        var student = studentRepository.findByIdAndDeletedAtIsNull(id)
            .orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.toResponseDto(student);
    }


    // method to update exisisting student record
    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {

        Student existing = studentRepository.findByIdAndDeletedAtIsNull(id)
            .orElseThrow(() -> new StudentNotFoundException(id));

        studentRepository.findByEmail(dto.getEmail())
            .ifPresent(student -> {
                if (!student.getId().equals(id)) {
                    throw new DuplicateEmailException(dto.getEmail());
                }
            });

        // I-update lahat ng fields
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setCourse(dto.getCourse());
        existing.setYearLevel(dto.getYearLevel());

        Student updated = studentRepository.save(existing);
        return studentMapper.toResponseDto(updated);
    }


    // method to delete student record
    public void deleteStudent(Long id) {
        var student = studentRepository.findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new StudentNotFoundException(id));

        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
    }
}