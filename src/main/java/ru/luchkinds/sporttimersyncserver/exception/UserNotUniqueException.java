package ru.luchkinds.sporttimersyncserver.exception;

public class UserNotUniqueException extends RuntimeException {
    public UserNotUniqueException(String s) {
        super(s);
    }
}
