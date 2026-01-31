package com.reportai.dto.student;

import java.time.LocalDate;

public record StudentUpdateDto(
        String name,
        String responsible,
        Long roomId,
        LocalDate dateOfBirth
) {
}
