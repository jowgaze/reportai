package com.reportai.dto.student;

import jakarta.validation.constraints.NotBlank;

public record StudentRequestDto(
        @NotBlank
        String name) {
}
