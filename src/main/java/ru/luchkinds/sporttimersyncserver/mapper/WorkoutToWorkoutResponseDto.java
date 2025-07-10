package ru.luchkinds.sporttimersyncserver.mapper;

import ru.luchkinds.sporttimersyncserver.data.entity.Workout;
import ru.luchkinds.sporttimersyncserver.presenter.dto.WorkoutResponseDto;

import java.util.function.Function;

public class WorkoutToWorkoutResponseDto implements Function<Workout, WorkoutResponseDto> {

    @Override
    public WorkoutResponseDto apply(Workout workout) {
        return new WorkoutResponseDto(
            workout.getId(), workout.getType(), workout.getDuration(), workout.getDate(), workout.getNotes()
        );
    }
}
