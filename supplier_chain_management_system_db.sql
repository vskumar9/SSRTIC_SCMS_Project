-- Create a new database 
CREATE DATABASE SCMS;

-- Use new created database
USE SCMS;

-- Create supplier table
CREATE TABLE supplier(
supplierId varchar(25) PRIMARY KEY,
supplierName varchar(50) NOT NULL,
contactPerson varchar(50),
email varchar(50) UNIQUE,
phone bigint UNIQUE
);

-- Create industrial_goods table
CREATE TABLE industrial_goods(
industryId varchar(25) PRIMARY KEY,
industry varchar(50) UNIQUE
);

-- Create consumer_goods table
CREATE TABLE consumer_goods(
consumerId varchar(25) PRIMARY KEY,
consumerGoods varchar(50) UNIQUE
);

-- Create products table
CREATE TABLE products(
productId varchar(25) PRIMARY KEY, 
productName varchar(50) NOT NULL, 
productDescription varchar(255), 
unitPrice double NOT NULL DEFAULT 0,
supplierId varchar(25),
industryId varchar(25),
consumerId varchar(25),
CONSTRAINT FK_supplier FOREIGN KEY (supplierId) REFERENCEs supplier(supplierId) ,
CONSTRAINT FK_industrialGoods FOREIGN KEY (industryId) REFERENCEs industrial_goods(industryId),
CONSTRAINT FK_consumerGoods FOREIGN KEY (consumerId) REFERENCEs consumer_goods(consumerId)
);

-- Show tables in database
SHOW TABLES;

-- describe supplier table structure
desc supplier;

-- describe industrial_goods table structure
DESC industrial_goods;

-- describe consumer_goods table structure
DESC consumer_goods;

-- describe products table structure
DESC products;


