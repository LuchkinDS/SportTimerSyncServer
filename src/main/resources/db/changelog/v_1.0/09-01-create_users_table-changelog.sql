-- liquibase formatted sql

-- changeset luchkinds:1752049319682-1
create table if not exists users
(
    id       integer not null constraint users_id_pk primary key autoincrement,
    username TEXT not null constraint users_username_pk unique,
    password TEXT
);
