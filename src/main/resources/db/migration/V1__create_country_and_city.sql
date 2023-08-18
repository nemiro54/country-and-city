CREATE TABLE countries
(
    id   UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    flag BYTEA
);

CREATE TABLE cities
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    country_id UUID REFERENCES countries (id),
    logo       BYTEA
);