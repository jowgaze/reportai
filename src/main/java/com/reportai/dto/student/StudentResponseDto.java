package com.reportai.dto.student;

import java.time.LocalDate;

public record StudentResponseDto(Long id, String name, String responsible, LocalDate dateOfBirth) {
}
