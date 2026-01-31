package com.reportai.dto.school;

import com.reportai.entity.School;

public class SchoolMapper {
    public static SchoolResponseDto toDto(School school){
        return new SchoolResponseDto(school.getId(), school.getName(), school.getRooms().size());
    }

    public static School toEntity(SchoolRequestDto request){
        return new School(request.name());
    }
}
