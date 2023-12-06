/*  SQL Variant for PostgreSQL - PL/pgSQL  */

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
  ('Mouse', 'MS010', 60, 10, 20);

INSERT INTO pos_simple.invoices (client_id, total_price, total_amount, created_at)
VALUES
  (1, 2500.50, 3, '2023-11-15'),
  (2, 1200.75, 2, '2023-11-16'),
  (3, 1800.25, 4, '2023-11-17'),
  (4, 3000.90, 1, '2023-11-18'),
  (5, 900.20, 3, '2023-11-19'),
  (1, 1500.60, 2, '2023-11-20'),
  (2, 2000.40, 5, '2023-11-21'),
  (3, 2800.80, 3, '2023-11-22'),
  (4, 2100.35, 4, '2023-11-23'),
  (5, 1700.45, 2, '2023-11-24');

INSERT INTO pos_simple.invoice_details (invoice_id, product_id, amount, price)
VALUES
  (1, 1, 2, 1200.75),
  (1, 3, 1, 500.20),
  (1, 6, 4, 800.40),
  (2, 2, 3, 750.60),
  (2, 5, 2, 200.90),
  (2, 8, 1, 80.35),
  (3, 4, 1, 350.25),
  (3, 7, 2, 60.80),
  (3, 10, 3, 60.20),
  (4, 1, 2, 1200.75),
  (4, 3, 1, 500.20),
  (4, 6, 4, 800.40),
  (5, 2, 3, 750.60),
  (5, 5, 2, 200.90),
  (5, 8, 1, 80.35),
  (6, 4, 1, 350.25),
  (6, 7, 2, 60.80),
  (6, 10, 3, 60.20),
  (7, 1, 2, 1200.75),
  (7, 3, 1, 500.20),
  (7, 6, 4, 800.40),
  (8, 2, 3, 750.60),
  (8, 5, 2, 200.90),
  (8, 8, 1, 80.35),
  (9, 4, 1, 350.25),
  (9, 7, 2, 60.80),
  (9, 10, 3, 60.20),
  (10, 1, 2, 1200.75),
  (10, 3, 1, 500.20),
  (10, 6, 4, 800.40);
