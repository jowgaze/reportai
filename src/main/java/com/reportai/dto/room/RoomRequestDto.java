package com.reportai.dto.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomRequestDto(@NotNull Long schoolId, @NotBlank String name) {
}