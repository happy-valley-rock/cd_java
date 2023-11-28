/*  SQL Variant for PostgreSQL - PL/pgSQL  */

CREATE TABLE api_rest.clients (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date DATE
);
