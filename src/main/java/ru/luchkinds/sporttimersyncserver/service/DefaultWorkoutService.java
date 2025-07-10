package ru.luchkinds.sporttimersyncserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;
import ru.luchkinds.sporttimersyncserver.data.entity.Workout;
import ru.luchkinds.sporttimersyncserver.data.entity.WorkoutType;
import ru.luchkinds.sporttimersyncserver.data.repository.WorkoutRepository;
import ru.luchkinds.sporttimersyncserver.exception.WorkoutNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultWorkoutService implements WorkoutServiceInterface {

    private final WorkoutRepository workoutRepository;

    @Override
    public Iterable<Workout> findAll(AppUser user, String filter) {
        if (filter.isEmpty()) {
            return workoutRepository.findAllByUser(user);
        }
        return workoutRepository.findAllByUser(user);
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
    public Optional<Workout> findById(AppUser user, int id) {
        return workoutRepository.findByIdAndUser(id, user);
    }

    @Override
    public void update(AppUser user, Integer id, WorkoutType type, Integer duration, String date, String notes) {
        var parsedDate = LocalDate.parse(date);
        workoutRepository
            .findByIdAndUser(id, user)
            .ifPresentOrElse(
                workout -> {
                    workout
                        .setType(type)
                        .setDate(parsedDate)
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
