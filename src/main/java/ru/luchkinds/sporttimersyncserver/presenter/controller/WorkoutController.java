package ru.luchkinds.sporttimersyncserver.presenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;
import ru.luchkinds.sporttimersyncserver.data.entity.Workout;
import ru.luchkinds.sporttimersyncserver.exception.WorkoutNotFoundException;
import ru.luchkinds.sporttimersyncserver.presenter.dto.CreateWorkoutDto;
import ru.luchkinds.sporttimersyncserver.presenter.dto.WorkoutResponseDto;
import ru.luchkinds.sporttimersyncserver.service.WorkoutServiceInterface;

import java.util.Map;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutServiceInterface workoutService;

    @GetMapping("workouts")
    public ResponseEntity<Iterable<WorkoutResponseDto>> workouts(@AuthenticationPrincipal AppUser user) {
        var workouts = workoutService.findAll(user, "");
        return ResponseEntity.ok(workouts);
    }

    @PostMapping("workouts")
    public ResponseEntity<Workout> addWorkout(
        @RequestBody @Validated CreateWorkoutDto dto,
        BindingResult bindingResult,
        @AuthenticationPrincipal AppUser user,
        UriComponentsBuilder uriBuilder
    ) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        var workout = workoutService.create(
            user, dto.type(), dto.duration(), dto.date(), dto.notes()
        );
        var uri = uriBuilder.path("/api/v1/users/workouts/{id}").build(Map.of("id", workout.getId()));
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("workouts/{id:\\d+}")
    public ResponseEntity<WorkoutResponseDto> getWorkout(@AuthenticationPrincipal AppUser user, @PathVariable Integer id) {
        var workout = workoutService.findById(user, id);
        return ResponseEntity.ok(workout);
    }

    @PutMapping("workouts/{id:\\d+}")
    public ResponseEntity<Workout> updateWorkout(
        @AuthenticationPrincipal AppUser user,
        @PathVariable Integer id,
        @RequestBody @Validated CreateWorkoutDto dto,
        BindingResult bindingResult
    ) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        workoutService.update(user, id, dto.type(), dto.duration(), dto.date(), dto.notes());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("workouts/{id:\\d+}")
    public ResponseEntity<Workout> deleteWorkout(@AuthenticationPrincipal AppUser user, @PathVariable Integer id) {
        workoutService.delete(user, id);
        return ResponseEntity.noContent().build();
    }
}
