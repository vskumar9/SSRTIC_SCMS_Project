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

-- Create warehouse_storage table
CREATE TABLE warehouse_storage(
warehouseId varchar(25),
inventoryId varchar(25),
CONSTRAINT Fk_warehouseId FOREIGN KEY (warehouseId) REFERENCES warehouse(warehouseId) ON DELETE RESTRICT,
CONSTRAINT Fk_inventoryId FOREIGN KEY (inventoryId) REFERENCES inventory(inventoryId) ON DELETE RESTRICT
);

-- Create customer table
CREATE TABLE customer (
customerID VARCHAR(25) PRIMARY KEY,
firstName VARCHAR(50) NOT NULL,
lastName VARCHAR(50) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
phone BIGINT UNIQUE NOT NULL,
address VARCHAR(255),
city VARCHAR(50),
state VARCHAR(50),
zipCode VARCHAR(20)
);


-- Create orders table
CREATE TABLE orders(
orderId VARCHAR(25) PRIMARY KEY,
customerId VARCHAR(25),
orderDate  datetime DEFAULT NOW(),
totalAmount BIGINT DEFAULT 0,
orderStatus VARCHAR(25),
CONSTRAINT Fk_customerId FOREIGN KEY (customerId) REFERENCES customer(customerId) ON DELETE RESTRICT
);

CREATE TABLE order_details(
orderId VARCHAR(25),
productId VARCHAR(25),
quantity INT,
CONSTRAINT Fk_orderId FOREIGN KEY (orderId) REFERENCES orders(orderId) ON DELETE RESTRICT,
CONSTRAINT Fk_order_productId FOREIGN KEY (productId) REFERENCES products(productId) ON DELETE RESTRICT
);

-- Sample data for the Customer table
INSERT INTO customer VALUES
('CUMR123456789098765','John', 'Doe', 'john.doe@example.com', 9874586325, '123 Main St', 'Anytown', 'CA', '12345'),
('CUMR123456789098764','Jane', 'Smith', 'jane.smith@example.com', 9874586328, '456 Oak Ave', 'Somewhere', 'NY', '67890'),
('CUMR123456789098763','Bob', 'Johnson', 'bob.johnson@example.com', 9874586327, '789 Pine Rd', 'Nowhere', 'TX', '54321');

DROP TABLE order_details;

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

DESC warehouse;

DESC warehouse_storage;

DESC customer;

DESC orders;

DESC order_details;

select * from products_information;
select * from products;
select * from supplier;
select * from industrial_goods;
select * from consumer_goods;
select * from inventory;
select * from warehouse;
select * from warehouse_storage;
select * from customer;
select * from orders;
select * from order_details;

truncate table orders;

select inventoryId, productId, productName, unitPrice, quntityInStock, lastStockUpdate from warehouse_storage natural join warehouse natural join inventory natural join products where warehouseId = 'WRHS241141023834582';

delete from warehouse where warehouseId = 'WRHS241141023834582';

DELIMITER //
CREATE TRIGGER update_current_capacity
AFTER UPDATE ON inventory
FOR EACH ROW
BEGIN
    DECLARE deltaQuantity INT;
    
    -- Calculate the change in quantity
    SET deltaQuantity = NEW.quntityInStock - OLD.quntityInStock;

    -- Update currentCapacity in warehouse for the corresponding warehouse
    UPDATE warehouse
    SET currentCapacity = currentCapacity + deltaQuantity
    WHERE warehouseId = (SELECT warehouseId FROM warehouse_storage WHERE inventoryId = NEW.inventoryId);
END;
//
DELIMITER ;

DROP TRIGGER IF EXISTS update_current_capacity;