package ru.luchkinds.sporttimersyncserver.data.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "workouts")
@Builder
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private WorkoutType type;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private LocalDate date;

    @Column()
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
