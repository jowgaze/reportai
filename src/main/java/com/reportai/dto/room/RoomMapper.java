package com.reportai.dto.room;

import com.reportai.entity.Room;
import com.reportai.entity.School;

public class RoomMapper {
    public static RoomResponseDto toDto(Room room){
        return new RoomResponseDto(
                room.getId(),
                room.getName(),
                room.getSchool().getName(),
                room.getStudents().size()
        );
    }

    public static Room toEntity(RoomRequestDto request, School school){
        return new Room(request.name(), school);
    }
}
