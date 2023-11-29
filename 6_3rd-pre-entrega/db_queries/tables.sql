/*  SQL Variant for PostgreSQL - PL/pgSQL  */

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
