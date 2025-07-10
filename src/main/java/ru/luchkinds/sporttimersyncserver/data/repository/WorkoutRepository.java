package ru.luchkinds.sporttimersyncserver.data.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.luchkinds.sporttimersyncserver.data.entity.AppUser;
import ru.luchkinds.sporttimersyncserver.data.entity.Workout;

import java.util.Optional;
import java.util.stream.Stream;

public interface WorkoutRepository extends CrudRepository<Workout, Integer> {
    Stream<Workout> findAllByUser(AppUser user);

    @Modifying
    @Query("DELETE Workout WHERE id = :id and user = :user")
    void deleteByIdAndUser(Integer id, AppUser user);

    Optional<Workout> findByIdAndUser(Integer id, AppUser user);
}
