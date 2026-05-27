package com.lorenz.student_management.dto.response_dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;





@Data
@Builder
public class ErrorResponseDto {
    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
}