CREATE DATABASE sells;
USE sells;

CREATE TABLE clients(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(75),
    lastname VARCHAR(75),
    dni VARCHAR(11)
);

CREATE TABLE invoices(
    id INT PRIMARY KEY AUTO_INCREMENT,
    clients_id INT,
    create_ad DATETIME,
    total double,

    CONSTRAINT fk_clients_id FOREIGN KEY (clients_id) REFERENCES clients(id)
);

CREATE TABLE invoices_details(
    id INT PRIMARY KEY  AUTO_INCREMENT,
    invoices_id INT,
    amount INT,
    products_id INT,
    price double,

    CONSTRAINT fk_invoices_id FOREIGN KEY (invoices_id) REFERENCES invoices(id),
    CONSTRAINT fk_products_id FOREIGN KEY (products_id) REFERENCES products(id)
);

CREATE TABLE products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(150),
    code VARCHAR(50),
    stock INT,
    price DECIMAL(10,2)
);
