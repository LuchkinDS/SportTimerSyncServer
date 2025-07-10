package ru.luchkinds.sporttimersyncserver.presenter.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.BindParam;

public record CreateUserDto(
    @BindParam("username")
    @NotNull
    @Length(min = 3)
    String username,
    @BindParam("password")
    @NotNull
    @Length(min = 8)
    String password
) {
}
