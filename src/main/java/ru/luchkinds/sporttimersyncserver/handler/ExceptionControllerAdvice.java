package ru.luchkinds.sporttimersyncserver.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.luchkinds.sporttimersyncserver.exception.UserNotUniqueException;
import ru.luchkinds.sporttimersyncserver.exception.WorkoutNotFoundException;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(WorkoutNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleWorkoutNotFoundException(WorkoutNotFoundException ex, Locale locale) {
        var problem = ProblemDetail
            .forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                messageSource.getMessage(ex.getMessage(), new Object[0], locale)
            );
        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(UserNotUniqueException.class)
    public ResponseEntity<ProblemDetail> handleUserNotUniqueException(UserNotUniqueException ex, Locale locale) {
        var problem = ProblemDetail
                .forStatusAndDetail(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        messageSource.getMessage(ex.getMessage(), new Object[0], locale)
                );
        return ResponseEntity.unprocessableEntity().body(problem);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(
            BindException ex,
            Locale locale
    ) {
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(
                        HttpStatus.BAD_REQUEST,
                        messageSource.getMessage(
                                "workout.errors.bad_request",
                                new Object[0],
                                locale
                        )
                );
        problemDetail.setProperty("message", ex
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage));
        return ResponseEntity.badRequest().body(problemDetail);
    }
}
