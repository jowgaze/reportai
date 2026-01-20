package com.reportai.dto.daily;

import com.reportai.entity.Daily;

public class DailyMapper {
    public static DailyResponseDto toDto(Daily daily){
        return new DailyResponseDto(
                daily.getId(),
                daily.getDate(),
                daily.getContent()
        );
    }
}
