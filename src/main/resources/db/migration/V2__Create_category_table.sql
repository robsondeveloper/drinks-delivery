CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(128) NOT NULL,
    code CHARACTER VARYING(128) NOT NULL,
    UNIQUE (name)
);