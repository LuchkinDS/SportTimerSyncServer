package ru.luchkinds.sporttimersyncserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;
import ru.luchkinds.sporttimersyncserver.data.repository.AppUserRepository;
import ru.luchkinds.sporttimersyncserver.exception.UserNotUniqueException;

@Component
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public AppUser create(String username, String password) {
        if (appUserRepository.findByUsername(username).isPresent()) {
            throw new UserNotUniqueException("user.errors.user_not_unique");
        }
        var user = AppUser
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        return appUserRepository.save(user);
    }
}
