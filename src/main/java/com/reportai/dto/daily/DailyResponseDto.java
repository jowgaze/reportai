package com.reportai.dto.daily;

import java.time.LocalDate;

public record DailyResponseDto(Long id, LocalDate date, String content) {
}
