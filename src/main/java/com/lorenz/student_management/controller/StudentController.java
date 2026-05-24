package com.lorenz.student_management.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.lorenz.student_management.model.Student;
import com.lorenz.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;






@RestController 
// use to create REST APIs
// a class that handles HTTP request and returns data (JSON)
@RequestMapping("/api/students")
// use at the class level to define a base URL for a controller / methods
// all methods inside will start with /api/students
@RequiredArgsConstructor
public class StudentController {
    
    private final StudentService studentService;

    // method to add or create student record
    @PostMapping
    // handle HTTP POST request (create / add data)
    @ResponseStatus(HttpStatus.CREATED)
    // tells the server what HTTP status to return
    // returns 201 CREATED status
    public Student create(@RequestBody Student student) {
        // @RequestBody - get the data sent by the client
        return studentService.createStudent(student);
    }

    // method to retrieve all students information
    @GetMapping // handle HTTP GET request(retrieve data from the database)
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }
}