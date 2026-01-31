package com.reportai.dto.room;

import jakarta.validation.constraints.NotBlank;

public record RoomUpdateDto (@NotBlank String name){
}
