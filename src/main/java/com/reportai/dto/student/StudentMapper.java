package com.reportai.dto.student;

import com.reportai.entity.Room;
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

    public static Student toEntity(StudentRequestDto request, Room room){
        return new Student(
                request.name(),
                request.responsible(),
                request.dateOfBirth(),
                room
        );
    }
}
