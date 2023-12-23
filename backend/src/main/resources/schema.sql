DROP TABLE if exists users;
CREATE TABLE IF NOT EXISTS users
(
    ID       int           not null AUTO_INCREMENT,
    name     varchar       not null,
    password varchar       not null,
    email    varchar       not null,
    roles    varchar array not null,
    UNIQUE (email),
    PRIMARY KEY (ID)
);


