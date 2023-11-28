/*  SQL Variant for PostgreSQL - PL/pgSQL  */

DROP DATABASE IF EXISTS api_rest;

CREATE DATABASE api_rest;
CREATE SCHEMA api_rest;

CREATE TABLE api_rest.clients (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date DATE
);

INSERT INTO api_rest.clients (first_name, last_name, birth_date) VALUES ('Luca', 'Prodan', '1953-05-17 00:00:00');
INSERT INTO api_rest.clients (first_name, last_name, birth_date) VALUES ('Gustavo', 'Cerati', '1959-08-11 00:00:00');
INSERT INTO api_rest.clients (first_name, last_name, birth_date) VALUES ('Luis', 'Spinetta', '1950-01-23 00:00:00');
INSERT INTO api_rest.clients (first_name, last_name, birth_date) VALUES ('Charly', 'Garcia', '1951-10-23 00:00:00');
