package com.lorenz.student_management.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.lorenz.student_management.dto.request_dto.StudentRequestDto;
import com.lorenz.student_management.dto.response_dto.StudentResponseDto;
import com.lorenz.student_management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;






@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {
    
    private final StudentService studentService;

    
    // method to add or create student record
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto create(@Valid @RequestBody StudentRequestDto student) {
        return studentService.createStudent(student);
    }


    // method to retrieve all students information
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(students); 
    }


    // method to search student
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDto>> search(
        @RequestParam(required = false) String keyword) {

        var result = studentService.searchStudent(keyword);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }


    // method to update student information
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDto update(
        @PathVariable(name = "id") Long id, 
        @Valid @RequestBody StudentRequestDto updated) {

        return studentService.updateStudent(id, updated);
    }


    // method to delete student information
    @DeleteMapping("/{id}") 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        studentService.deleteStudent(id);
    }
}