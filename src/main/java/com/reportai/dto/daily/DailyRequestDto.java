package com.reportai.dto.daily;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DailyRequestDto(
        @NotNull
        Long studentId,

        @NotBlank
        String content) {
}
