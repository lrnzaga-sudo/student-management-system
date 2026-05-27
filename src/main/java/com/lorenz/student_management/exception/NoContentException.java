package com.lorenz.student_management.exception;

public class NoContentException extends RuntimeException {
    public NoContentException() {
        super("No data found");
    }
}
