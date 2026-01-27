package com.reportai.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentRequestDto(
        @NotBlank
        String name,

        @NotBlank
        String responsible,

        @NotNull
        LocalDate dateOfBirth
        ) {
}
