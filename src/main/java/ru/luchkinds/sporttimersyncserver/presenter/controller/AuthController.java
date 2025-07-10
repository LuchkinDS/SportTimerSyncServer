package ru.luchkinds.sporttimersyncserver.presenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.luchkinds.sporttimersyncserver.presenter.dto.CreateUserDto;
import ru.luchkinds.sporttimersyncserver.service.AppUserDetailService;

@RestController
@RequestMapping(value = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserDetailService service;

    @PostMapping(value = "sign-up")
    public ResponseEntity<?> signUp(
        @RequestBody @Validated CreateUserDto createUserDto,
        BindingResult bindingResult
    ) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        service.create(createUserDto.username(), createUserDto.password());

        return ResponseEntity
                .accepted().build();
    }
}
