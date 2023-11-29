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
  total DOUBLE PRECISION,
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
  ('Luca', 'Prodan', '12345', 'Passport'),
  ('Gustavo', 'Cerati', '67890', 'ID Card'),
  ('Luis', 'Spinetta', '54321', 'Driver License'),
  ('Charly', 'Garcia', '98765', 'Passport'),
  ('Andres', 'Calamaro', '13579', 'Social Security');

INSERT INTO pos_simple.products (description, code, stock, purchase_price, sell_price)
VALUES
  ('Laptop', 'LT001', 50, 800, 1200),
  ('Printer', 'PR002', 30, 150, 250),
  ('Smartphone', 'SP003', 100, 300, 500),
  ('Tablet', 'TB004', 40, 200, 350),
  ('Headphones', 'HD005', 80, 50, 100),
  ('Desk Chair', 'DC006', 20, 100, 200),
  ('Coffee Maker', 'CM007', 15, 30, 60),
  ('Bluetooth Speaker', 'BS008', 50, 40, 80),
  ('External Hard Drive', 'HD009', 25, 80, 120),
  ('Mouse', 'MS010', 60, 10, 20),
  ('Keyboard', 'KB011', 55, 15, 30),
  ('Monitor', 'MN012', 35, 200, 300),
  ('Desk Lamp', 'DL013', 40, 25, 50),
  ('Backpack', 'BP014', 70, 40, 80),
  ('Sunglasses', 'SG015', 90, 15, 30),
  ('Watch', 'WT016', 65, 50, 100),
  ('Gaming Console', 'GC017', 10, 300, 500),
  ('Game Controller', 'GC018', 25, 40, 80),
  ('Digital Camera', 'DC019', 15, 150, 250),
  ('Fitness Tracker', 'FT020', 30, 60, 100);

INSERT INTO pos_simple.invoices (client_id, total, created_at)
VALUES
  (1, 2500, '2023-11-15'),
  (2, 1200, '2023-11-16'),
  (3, 1800, '2023-11-17'),
  (4, 3000, '2023-11-18'),
  (5, 900, '2023-11-19'),
  (1, 1500, '2023-11-20'),
  (2, 2000, '2023-11-21'),
  (3, 2800, '2023-11-22'),
  (4, 2100, '2023-11-23'),
  (5, 1700, '2023-11-24');

INSERT INTO pos_simple.invoice_details (invoice_id, product_id, amount, price)
VALUES
  (1, 1, 2, 1200),
  (1, 3, 1, 500),
  (1, 6, 4, 800),
  (2, 2, 3, 750),
  (2, 5, 2, 200),
  (2, 8, 1, 80),
  (3, 4, 1, 350),
  (3, 7, 2, 60),
  (3, 10, 3, 60),
  (4, 1, 2, 1200),
  (4, 3, 1, 500),
  (4, 6, 4, 800),
  (5, 2, 3, 750),
  (5, 5, 2, 200),
  (5, 8, 1, 80),
  (6, 4, 1, 350),
  (6, 7, 2, 60),
  (6, 10, 3, 60),
  (7, 1, 2, 1200),
  (7, 3, 1, 500),
  (7, 6, 4, 800),
  (8, 2, 3, 750),
  (8, 5, 2, 200),
  (8, 8, 1, 80),
  (9, 4, 1, 350),
  (9, 7, 2, 60),
  (9, 10, 3, 60),
  (10, 1, 2, 1200),
  (10, 3, 1, 500),
  (10, 6, 4, 800);
