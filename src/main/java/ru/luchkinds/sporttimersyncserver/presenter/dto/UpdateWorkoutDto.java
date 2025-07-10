package ru.luchkinds.sporttimersyncserver.presenter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.BindParam;
import ru.luchkinds.sporttimersyncserver.data.entity.WorkoutType;

import java.time.LocalDate;

public record UpdateWorkoutDto(
        @BindParam("type")
        @NotNull
        WorkoutType type,

        @BindParam("duration")
        @NotNull
        @Positive
        Integer duration,

        @BindParam("date")
        @DateTimeFormat(pattern = "YYYY-mm-dd")
        @NotNull
        LocalDate date,

        @BindParam("notes")
        @Size(max = 50)
        String notes
) {
}
