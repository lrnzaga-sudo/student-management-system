package com.lorenz.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lorenz.student_management.model.Student;

@Repository // class or interface responsible for retrieving, creating, updating, and deleting data in the database
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository - interface that provides ready-made methods to interact with database
    // Student - table(@Entity)
    // Long - type of the primary key
}