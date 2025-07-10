-- liquibase formatted sql

-- changeset luchkinds:1752053639451-1
create table if not exists workouts
(
    id       integer not null constraint users_id_pk primary key autoincrement,
    `type` TEXT not null,
    duration integer not null,
    `date` TEXT not null,
    notes TEXT,
    user_id INTEGER,
    FOREIGN KEY (user_id)  REFERENCES users (id)
);