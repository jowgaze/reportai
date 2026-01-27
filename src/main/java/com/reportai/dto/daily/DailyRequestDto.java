package com.reportai.dto.daily;

import jakarta.validation.constraints.NotBlank;

public record DailyRequestDto(
        @NotBlank
        String content) {
}
