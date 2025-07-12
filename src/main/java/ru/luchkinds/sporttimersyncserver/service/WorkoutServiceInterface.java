package ru.luchkinds.sporttimersyncserver.service;

import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;
import ru.luchkinds.sporttimersyncserver.data.entity.Workout;
import ru.luchkinds.sporttimersyncserver.data.entity.WorkoutType;
import ru.luchkinds.sporttimersyncserver.presenter.dto.WorkoutResponseDto;

import java.time.LocalDate;

public interface WorkoutServiceInterface {
    Iterable<WorkoutResponseDto> findAll(AppUser user, String filter);

    Workout create(AppUser user, WorkoutType type, Integer duration, LocalDate date, String notes);

    WorkoutResponseDto findById(int id, AppUser user);

    void update(Integer id, AppUser user, WorkoutType type, Integer duration, LocalDate date, String notes);

    void delete(Integer id, AppUser user);
}
