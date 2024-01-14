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
industry varchar(50) UNIQUE,
industry_description varchar(255) 
);

-- Create consumer_goods table
CREATE TABLE consumer_goods(
consumerId varchar(25) PRIMARY KEY,
consumerGoods varchar(50) UNIQUE,
consumer_description varchar(255) 
);

-- Create products table
CREATE TABLE products(
productId varchar(25) PRIMARY KEY, 
productName varchar(50) NOT NULL, 
productDescription varchar(255), 
unitPrice double DEFAULT 0
);

-- Create product information
CREATE TABLE products_information(
productInfoId varchar(25) PRIMARY KEY,
productId varchar(25),
supplierId varchar(25),
industryId varchar(25),
consumerId varchar(25),
CONSTRAINT FK_productId FOREIGN KEY (productId) REFERENCES products(productId) ON DELETE RESTRICT,
CONSTRAINT FK_supplier FOREIGN KEY (supplierId) REFERENCES supplier(supplierId) ON DELETE RESTRICT,
CONSTRAINT FK_industrialGoods FOREIGN KEY (industryId) REFERENCES industrial_goods(industryId) ON DELETE RESTRICT,
CONSTRAINT FK_consumerGoods FOREIGN KEY (consumerId) REFERENCES consumer_goods(consumerId)ON DELETE RESTRICT
);

-- Create Inventory table
CREATE TABLE inventory(
inventoryId varchar(25) PRIMARY KEY,
productId varchar(25),
quntityInStock bigint DEFAULT 0,
lastStockUpdate datetime DEFAULT NOW(),
CONSTRAINT Fk_inventory_productId FOREIGN KEY (productId) REFERENCES products(productId) ON DELETE RESTRICT
);

-- Create warehouse table
CREATE TABLE warehouse(
warehouseId varchar(25) PRIMARY KEY,
warehouseName varchar(30),
location varchar(100),
capacity bigint,
currentCapacity bigint
);

CREATE TABLE warehouse_storage(
warehouseId varchar(25),
inventoryId varchar(25),
CONSTRAINT Fk_warehouseId FOREIGN KEY (warehouseId) REFERENCES warehouse(warehouseId) ON DELETE RESTRICT,
CONSTRAINT Fk_inventoryId FOREIGN KEY (inventoryId) REFERENCES inventory(inventoryId) ON DELETE RESTRICT
);


DROP TABLE warehousestorage;

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

-- describe product information table structure
DESC products_information;

-- describe inventory table structure
DESC inventory;


select * from products_information;
select * from products;
select * from supplier;
select * from industrial_goods;
select * from consumer_goods;
select * from inventory;
select * from warehouse;
select * from warehouse_storage;

truncate table warehouse;