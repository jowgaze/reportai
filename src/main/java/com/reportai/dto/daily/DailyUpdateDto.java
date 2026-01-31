package com.reportai.dto.daily;

import jakarta.validation.constraints.NotBlank;

public record DailyUpdateDto(@NotBlank String content) {
}
