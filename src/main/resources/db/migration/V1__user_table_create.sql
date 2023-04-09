CREATE SCHEMA "leapxpert";

CREATE TABLE "leapxpert"."user"
(
    id SERIAL PRIMARY KEY,
    first_name  TEXT NOT NULL,
    last_name   TEXT NOT NULL,
    email       TEXT,
    phone       TEXT
);