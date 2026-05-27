package com.lorenz.student_management.exception;

// Para sa duplicate email
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("Email already exists: " + email);
    }
}