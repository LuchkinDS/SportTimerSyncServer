package ru.luchkinds.sporttimersyncserver.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;
import ru.luchkinds.sporttimersyncserver.data.entity.Workout;
import ru.luchkinds.sporttimersyncserver.data.entity.WorkoutType;
import ru.luchkinds.sporttimersyncserver.data.repository.WorkoutRepository;
import ru.luchkinds.sporttimersyncserver.exception.WorkoutNotFoundException;
import ru.luchkinds.sporttimersyncserver.mapper.WorkoutToWorkoutResponseDto;
import ru.luchkinds.sporttimersyncserver.presenter.dto.UpdateWorkoutDto;
import ru.luchkinds.sporttimersyncserver.presenter.dto.WorkoutResponseDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

class DefaultWorkoutServiceTest {

    @Test
    @DisplayName("тест findAllByUser()")
    void findAll() {
        var workout1 = Workout
                .builder()
                .id(1)
                .type(WorkoutType.Cardio)
                .date(LocalDate.parse("2018-01-01"))
                .duration(1)
                .notes("notes 1")
                .build();
        var workout2 = Workout
                .builder()
                .id(2)
                .type(WorkoutType.Strength)
                .date(LocalDate.parse("2018-02-01"))
                .duration(10)
                .build();
        var user = AppUser.builder().build();
        var mockWorkoutRepository = Mockito.mock(WorkoutRepository.class);
        Mockito.when(mockWorkoutRepository.findAllByUser(user)).thenReturn(Stream.of(workout1, workout2));
        var workoutService = new DefaultWorkoutService(mockWorkoutRepository);
        var actualWorkouts = workoutService.findAll(user, "");
        Iterable<WorkoutResponseDto> expectedWorkouts = Stream
            .of(workout1, workout2)
            .map(w -> new WorkoutToWorkoutResponseDto().apply(w))
            .toList();
        Assertions.assertEquals(expectedWorkouts, actualWorkouts);
    }

    @Test
    void create() {
    }

    @Test
    @DisplayName("тест findByIdAndUser(), запись существует")
    void findByIdExistWorkout() {
        var workout1 = Workout
                .builder()
                .id(11)
                .type(WorkoutType.Cardio)
                .date(LocalDate.parse("2025-01-01"))
                .duration(1)
                .build();
        var user = AppUser.builder().build();
        var mockWorkoutRepository = Mockito.mock(WorkoutRepository.class);
        Mockito.when(mockWorkoutRepository.findByIdAndUser(11, user)).thenReturn(Optional.of(workout1));
        var workoutService = new DefaultWorkoutService(mockWorkoutRepository);
        var actualWorkout = workoutService.findById(11, user);
        var expectedWorkout = new WorkoutToWorkoutResponseDto().apply(workout1);
        Assertions.assertEquals(expectedWorkout, actualWorkout);
    }

    @Test
    @DisplayName("тест findByIdAndUser(), запись не существует")
    void findByIdNotExistWorkout() {
        var user = AppUser.builder().build();
        var mockWorkoutRepository = Mockito.mock(WorkoutRepository.class);
        Mockito.when(mockWorkoutRepository.findByIdAndUser(11, user)).thenReturn(Optional.empty());
        var workoutService = new DefaultWorkoutService(mockWorkoutRepository);
        Assertions.assertThrows(WorkoutNotFoundException.class, () -> workoutService.findById(11, user));
    }

    @Test
    @DisplayName("тест updateByIdAndUser(), запись не существует")
    void updateWithExistWorkout() {
        var workout1 = new UpdateWorkoutDto(WorkoutType.Cardio, 1, LocalDate.parse("2025-01-01"), null);
        var user = AppUser.builder().build();
        var mockWorkoutRepository = Mockito.mock(WorkoutRepository.class);
        Mockito.when(mockWorkoutRepository.findByIdAndUser(1, user)).thenReturn(Optional.empty());
        var workoutService = new DefaultWorkoutService(mockWorkoutRepository);
        Assertions.assertThrows(WorkoutNotFoundException.class, () -> workoutService
            .update(1, user, workout1.type(), workout1.duration(), workout1.date(), workout1.notes()));
    }
}