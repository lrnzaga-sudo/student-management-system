package com.lorenz.student_management.dto.request_dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;




@Data
public class StudentRequestDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Course is required")
    private String course;

    @Min(value = 1, message = "Year level must be at least 1")
    @Max(value = 4, message = "Year level must not exceed 4")
    private int yearLevel;
}