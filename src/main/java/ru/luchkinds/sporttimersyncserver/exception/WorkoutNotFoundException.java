package ru.luchkinds.sporttimersyncserver.exception;

public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException(String s) {
        super(s);
    }
}
