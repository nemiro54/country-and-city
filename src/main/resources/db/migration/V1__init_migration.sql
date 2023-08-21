CREATE TABLE IF NOT EXISTS countries
(
    id   UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    flag BYTEA
);

CREATE TABLE IF NOT EXISTS cities
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    country_id UUID REFERENCES countries (id),
    logo       BYTEA
);

CREATE TABLE IF NOT EXISTS users
(
    id UUID PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    id   UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id UUID REFERENCES users (id),
    role_id UUID REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);