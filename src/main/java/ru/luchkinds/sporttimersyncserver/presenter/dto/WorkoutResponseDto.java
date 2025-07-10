package ru.luchkinds.sporttimersyncserver.presenter.dto;

import ru.luchkinds.sporttimersyncserver.data.entity.WorkoutType;

import java.time.LocalDate;

public record WorkoutResponseDto(
    Integer id,
    WorkoutType type,
    Integer duration,
    LocalDate date,
    String notes
) {
}
