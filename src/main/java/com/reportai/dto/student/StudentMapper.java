package com.reportai.dto.student;

import com.reportai.entity.Student;

public class StudentMapper {
    public static StudentResponseDto toDto(Student student){
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getResponsible(),
                student.getDateOfBirth()
        );
    }
}
