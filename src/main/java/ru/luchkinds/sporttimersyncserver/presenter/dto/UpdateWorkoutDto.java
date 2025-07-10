package ru.luchkinds.sporttimersyncserver.presenter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.BindParam;
import ru.luchkinds.sporttimersyncserver.data.entity.WorkoutType;

import java.time.LocalDate;

public record UpdateWorkoutDto(
        @BindParam("type")
        @NotBlank
        WorkoutType type,

        @BindParam("duration")
        @NotBlank
        Integer duration,

        @BindParam("date")
        @NotBlank
        LocalDate date,

        @BindParam("notes")
        @Size(max = 50)
        String notes
) {
}
