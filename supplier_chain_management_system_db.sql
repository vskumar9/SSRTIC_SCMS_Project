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
quntityStock bigint DEFAULT 0,
lastUpdate datetime DEFAULT NOW(),
CONSTRAINT Fk_inventory_productId FOREIGN KEY (productId) REFERENCES products(productId) ON DELETE RESTRICT
);

DROP TABLE inventory;

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


SELECT 
    productInfoId,
    productId,
    productName,
    productDescription,
    unitPrice,
    supplierName,
    industry,
    consumerGoods
FROM 
    products_information
NATURAL JOIN 
    products
NATURAL JOIN 
    supplier
NATURAL JOIN 
    industrial_goods
NATURAL JOIN 
    consumer_goods;



SELECT 
    pi.productInfoId,
    p.productId,
    p.productName,
    p.productDescription,
    p.unitPrice,
    s.supplierName,
    ig.industry,
    cg.consumerGoods
FROM 
    products_information pi
JOIN 
    products p ON pi.productId = p.productId
JOIN 
    supplier s ON pi.supplierId = s.supplierId
JOIN 
    industrial_goods ig ON pi.industryId = ig.industryId
JOIN 
    consumer_goods cg ON pi.consumerId = cg.consumerId;


SELECT 
    pi.productInfoId,
    p.productId,
    p.productName,
    p.productDescription,
    p.unitPrice,
    s.supplierName,
    ig.industry,
    cg.consumerGoods
FROM 
    products_information pi
JOIN 
    products p ON pi.productId = p.productId
JOIN 
    supplier s ON pi.supplierId = s.supplierId
JOIN 
    industrial_goods ig ON pi.industryId = ig.industryId
JOIN 
    consumer_goods cg ON pi.consumerId = cg.consumerId
WHERE 
    pi.productInfoId = 'your_specific_productInfoId';

SELECT 
    pi.productInfoId,
    p.productId,
    p.productName,
    p.productDescription,
    p.unitPrice,
    s.supplierName,
    ig.industry
FROM 
    products_information pi
JOIN 
    products p ON pi.productId = p.productId
JOIN 
    supplier s ON pi.supplierId = s.supplierId
JOIN 
    industrial_goods ig ON pi.industryId = ig.industryId
WHERE 
    p.productName = 'your_specific_productName';


