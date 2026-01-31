package com.reportai.dto.school;

import jakarta.validation.constraints.NotBlank;

public record SchoolRequestDto(@NotBlank String name) {
}