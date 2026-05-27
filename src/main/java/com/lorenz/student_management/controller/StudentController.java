package com.lorenz.student_management.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.lorenz.student_management.dto.request_dto.StudentRequestDto;
import com.lorenz.student_management.dto.response_dto.StudentResponseDto;
import com.lorenz.student_management.model.Student;
import com.lorenz.student_management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;






@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
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
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    // method to update student information
    // @PutMapping("/{id}") // handle HTTP PUT request (update existing record in the database)
    // public Student update(@PathVariable(name = "id") Long id, @RequestBody Student updated) {
    //     // @PathVariable - used to extract/get values from the URL
    //     return studentService.updateStudent(id, updated);
    // }

    // method to delete student information
    @DeleteMapping("/{id}") // handle HTTP DELETE request(deleting record in the database)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        studentService.deleteStudent(id);
    }

    // method to search student
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDto search(@PathVariable(name = "id") Long id) {
        return studentService.getStudentById(id);
    }
}