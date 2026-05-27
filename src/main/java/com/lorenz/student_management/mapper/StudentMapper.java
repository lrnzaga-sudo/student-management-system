package com.lorenz.student_management.mapper;

import org.mapstruct.Mapper;
import com.lorenz.student_management.dto.request_dto.StudentRequestDto;
import com.lorenz.student_management.dto.response_dto.StudentResponseDto;
import com.lorenz.student_management.model.Student;





@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentRequestDto dto);

    StudentResponseDto toResponseDto(Student student);
}