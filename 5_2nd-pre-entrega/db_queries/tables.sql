/*  SQL Variant for PostgreSQL - PL/pgSQL  */

DROP TABLE IF EXISTS billing_project.clients;
DROP TABLE IF EXISTS billing_project.invoices;
DROP TABLE IF EXISTS billing_project.invoice_details;
DROP TABLE IF EXISTS billing_project.products;

CREATE TABLE billing_project.clients(
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(75),
  last_name VARCHAR(75),
  document_number VARCHAR(20),
  document_type VARCHAR(75)
);


CREATE TABLE billing_project.invoices(
  id SERIAL PRIMARY KEY,
  client_id INT,
  total DOUBLE,
  created_at DATE

  CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE billing_project.invoice_details(
  id SERIAL PRIMARY KEY,
  invoice_id INT,
  product_id INT,
  amount INT,
  price DOUBLE,

  CONSTRAINT fk_invoice_id FOREIGN KEY (invoice_id) REFERENCES invoices(id)
  CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE billing_project.products(
  id SERIAL PRIMARY KEY,
  description VARCHAR(150),
  code VARCHAR(50),
  stock INT,
  price DOUBLE
);
