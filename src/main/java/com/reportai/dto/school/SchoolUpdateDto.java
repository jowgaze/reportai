package com.reportai.dto.school;

import jakarta.validation.constraints.NotBlank;

public record SchoolUpdateDto(@NotBlank String name) {
}
