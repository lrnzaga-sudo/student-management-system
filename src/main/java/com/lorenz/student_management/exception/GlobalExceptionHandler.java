package com.lorenz.student_management.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.lorenz.student_management.dto.response_dto.ErrorResponseDto;






@RestControllerAdvice
public class GlobalExceptionHandler {

    // Duplicate email → 409 Conflict
    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleDuplicateEmail(DuplicateEmailException ex) {
        return ErrorResponseDto.builder()
            .status(409)
            .error("Conflict")
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
    }

    // Student not found → 404 Not Found
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNotFound(StudentNotFoundException ex) {
        return ErrorResponseDto.builder()
            .status(404)
            .error("Not Found")
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
    }

    // Validation errors (@Valid) → 400 Bad Request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

        return Map.of(
            "status", 400,
            "error", "Validation Failed",
            "fields", fieldErrors,
            "timestamp", LocalDateTime.now()
        );
    }
}