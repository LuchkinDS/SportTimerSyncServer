package ru.luchkinds.sporttimersyncserver.service;

import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;
import ru.luchkinds.sporttimersyncserver.data.entity.Workout;
import ru.luchkinds.sporttimersyncserver.data.entity.WorkoutType;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkoutServiceInterface {
    Iterable<Workout> findAll(AppUser user, String filter);

    Workout create(AppUser user, WorkoutType type, Integer duration, LocalDate date, String notes);

    Optional<Workout> findById(AppUser user, int id);

    void update(AppUser user, Integer id, WorkoutType type, Integer duration, String date, String notes);

    void delete(AppUser user, Integer id);
}
