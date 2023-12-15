DROP TABLE users;
CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     varchar not null,
    email    varchar not null,
    is_admin int     not null,
    UNIQUE (email)
);


