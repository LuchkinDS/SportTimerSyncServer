package ru.luchkinds.sporttimersyncserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;
import ru.luchkinds.sporttimersyncserver.data.entity.Workout;
import ru.luchkinds.sporttimersyncserver.data.entity.WorkoutType;
import ru.luchkinds.sporttimersyncserver.data.repository.WorkoutRepository;
import ru.luchkinds.sporttimersyncserver.exception.WorkoutNotFoundException;
import ru.luchkinds.sporttimersyncserver.mapper.WorkoutToWorkoutResponseDto;
import ru.luchkinds.sporttimersyncserver.presenter.dto.WorkoutResponseDto;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DefaultWorkoutService implements WorkoutServiceInterface {

    private final WorkoutRepository workoutRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<WorkoutResponseDto> findAll(AppUser user, String filter) {
        return workoutRepository
            .findAllByUser(user)
            .map(workout -> new WorkoutToWorkoutResponseDto().apply(workout))
            .toList();
    }

    @Override
    public Workout create(AppUser user, WorkoutType type, Integer duration, LocalDate date, String notes) {
        var workout = Workout
                .builder()
                .type(type)
                .duration(duration)
                .date(date)
                .notes(notes)
                .user(user)
                .build();
        return workoutRepository.save(workout);
    }

    @Override
    public WorkoutResponseDto findById(AppUser user, int id) {
        var workout = workoutRepository
            .findByIdAndUser(id, user).orElseThrow(() -> new WorkoutNotFoundException("workout.errors.workout_not_found"));
        return new WorkoutToWorkoutResponseDto().apply(workout);
    }

    @Override
    public void update(AppUser user, Integer id, WorkoutType type, Integer duration, LocalDate date, String notes) {
        workoutRepository
            .findByIdAndUser(id, user)
            .ifPresentOrElse(
                workout -> {
                    workout
                        .setType(type)
                        .setDate(date)
                        .setDuration(duration)
                        .setNotes(notes);
                        workoutRepository.save(workout);
                },
                () -> {
                    throw new WorkoutNotFoundException("workout.errors.workout_not_found");
                });
    }

    @Override
    @Transactional
    public void delete(AppUser user, Integer id) {
        workoutRepository.deleteByIdAndUser(id, user);
    }
}
