package com.lorenz.student_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;







// represents students table in database
@Entity // indicate that this class represents a table in the database
@Table(name = "students") // to specify the exact table in the database
@Data // shortcut to generate getters, setters, and other common methods automatically
public class Student {
    @Id // refers to students table primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // @GeneratedValue - generate value automatically
    // strategy = GenerationType.IDENTITY - database ang mag ge generate ng ID gamit ang AUTO-INCREMENT
    @Column(name = "student_id") // control the column name and rules (nullable, unique, etc.)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String course;

    private int yearLevel;
}