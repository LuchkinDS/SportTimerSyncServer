package ru.luchkinds.sporttimersyncserver.service;

import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;
import ru.luchkinds.sporttimersyncserver.data.entity.Workout;
import ru.luchkinds.sporttimersyncserver.data.entity.WorkoutType;
import ru.luchkinds.sporttimersyncserver.presenter.dto.WorkoutResponseDto;

import java.time.LocalDate;

public interface WorkoutServiceInterface {
    Iterable<WorkoutResponseDto> findAll(AppUser user, String filter);

    Workout create(AppUser user, WorkoutType type, Integer duration, LocalDate date, String notes);

    WorkoutResponseDto findById(AppUser user, int id);

    void update(AppUser user, Integer id, WorkoutType type, Integer duration, LocalDate date, String notes);

    void delete(AppUser user, Integer id);
}
