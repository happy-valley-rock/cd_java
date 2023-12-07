/*  SQL Variant for PostgreSQL - PL/pgSQL  */

DROP DATABASE IF EXISTS pos_simple CASCADE;
CREATE DATABASE pos_simple;

DROP SCHEMA IF EXISTS pos_simple CASCADE;
CREATE SCHEMA pos_simple;

DROP TABLE IF EXISTS pos_simple.clients CASCADE;
DROP TABLE IF EXISTS pos_simple.invoices CASCADE;
DROP TABLE IF EXISTS pos_simple.invoice_details CASCADE;
DROP TABLE IF EXISTS pos_simple.products CASCADE;

CREATE TABLE pos_simple.clients(
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(75),
  last_name VARCHAR(75),
  document_number VARCHAR(20),
  document_type VARCHAR(75)
);

CREATE TABLE pos_simple.invoices(
  id SERIAL PRIMARY KEY,
  client_id INT,
  total_price DOUBLE PRECISION,
  total_amount INT,
  created_at DATE,
  CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES pos_simple.clients(id)
);

CREATE TABLE pos_simple.products(
  id SERIAL PRIMARY KEY,
  description VARCHAR(150),
  code VARCHAR(50),
  stock INT,
  purchase_price DOUBLE PRECISION,
  sell_price DOUBLE PRECISION
);

CREATE TABLE pos_simple.invoice_details(
  id SERIAL PRIMARY KEY,
  invoice_id INT,
  product_id INT,
  amount INT,
  price DOUBLE PRECISION,
  CONSTRAINT fk_invoice_id FOREIGN KEY (invoice_id) REFERENCES pos_simple.invoices(id),
  CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES pos_simple.products(id)
);

INSERT INTO pos_simple.clients (first_name, last_name, document_number, document_type)
VALUES
  ('Luca', 'Prodan', '12345', 'DNI'),
  ('Gustavo', 'Cerati', '67890', 'DNI'),
  ('Luis', 'Spinetta', '54321', 'Driver License'),
  ('Charly', 'Garcia', '98765', 'Passport'),
  ('Andres', 'Calamaro', '13579', 'Social Security');

INSERT INTO pos_simple.products (description, code, stock, purchase_price, sell_price)
VALUES
  ('Sugar', 'SG001', 100, 1, 2),
  ('Soap', 'SP002', 50, 1.5, 3),
  ('Shampoo', 'SH003', 40, 2.5, 5),
  ('Toothpaste', 'TP004', 30, 1, 2),
  ('Rice', 'RC005', 80, 2, 4),
  ('Towel', 'TW006', 60, 3, 6),
  ('Milk', 'MK007', 50, 1.5, 3),
  ('Eggs', 'EG008', 70, 1, 2),
  ('Bread', 'BD009', 40, 1.2, 2.5),
  ('Cheese', 'CH010', 25, 3, 6),
  ('Tomatoes', 'TM011', 30, 0.8, 1.5),
  ('Chicken', 'CH012', 20, 5, 10),
  ('Cereal', 'CR013', 45, 2.5, 5),
  ('Toilet Paper', 'TP014', 55, 1.8, 3),
  ('Detergent', 'DT015', 40, 2, 4),
  ('Potatoes', 'PT016', 35, 1.2, 2.5),
  ('Bananas', 'BN017', 60, 0.5, 1),
  ('Coffee', 'CF018', 25, 4, 8),
  ('Juice', 'JU019', 30, 2, 4),
  ('Water Bottles', 'WB020', 75, 0.75, 1.5);

INSERT INTO pos_simple.invoices (client_id, total_price, total_amount, created_at)
VALUES
  (1, 25.75, 5, '2023-11-15'),
  (2, 18.50, 3, '2023-11-16'),
  (3, 32.40, 4, '2023-11-17'),
  (4, 14.90, 2, '2023-11-18'),
  (5, 45.30, 6, '2023-11-19'),
  (1, 22.60, 3, '2023-11-20'),
  (2, 37.80, 5, '2023-11-21'),
  (3, 28.20, 4, '2023-11-22'),
  (4, 19.35, 2, '2023-11-23'),
  (5, 11.45, 1, '2023-11-24');

INSERT INTO pos_simple.invoice_details (invoice_id, product_id, amount, price)
VALUES
  (1, 1, 2, 2),
  (1, 3, 1, 5),
  (1, 6, 4, 6),
  (2, 2, 3, 3),
  (2, 5, 2, 4),
  (2, 8, 1, 3),
  (3, 4, 1, 4),
  (3, 7, 2, 6),
  (3, 10, 3, 7),
  (4, 1, 2, 2),
  (4, 3, 1, 5),
  (4, 6, 4, 6),
  (5, 2, 3, 3),
  (5, 5, 2, 4),
  (5, 8, 1, 3),
  (6, 4, 1, 4),
  (6, 7, 2, 6),
  (6, 10, 3, 7),
  (7, 1, 2, 2),
  (7, 3, 1, 5),
  (7, 6, 4, 6),
  (8, 2, 3, 3),
  (8, 5, 2, 4),
  (8, 8, 1, 3),
  (9, 4, 1, 4),
  (9, 7, 2, 6),
  (9, 10, 3, 7),
  (10, 1, 2, 2),
  (10, 3, 1, 5),
  (10, 6, 4, 6);
