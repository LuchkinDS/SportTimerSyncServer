package ru.luchkinds.sporttimersyncserver.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
}
