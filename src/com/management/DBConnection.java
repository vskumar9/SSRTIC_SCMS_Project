package com.management;
import java.sql.*;
import java.util.*;
import java.io.*;

public class DBConnection {
	
	private static Connection con = null;
	private static Properties prop = new Properties();

	// Establish Database Connection method 
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
	    String databaseName = "SCMS";
	    String tableName1 = "supplier";
	    String tableName2 = "industrial_goods";
	    String tableName3 = "consumer_goods";
	    String tableName4 = "products";
	    String tableName5 = "products_information";
	    
	    String triggerName = "update_current_capacity";

	    try (FileInputStream file = new FileInputStream("src/DB.properties")) {
	        prop.load(file);
	        Class.forName(prop.getProperty("DB_DRIVER_CLASS"));

	        // Check if the database exists
	        if (!databaseExists(databaseName)) {
	            // Create the database
	            createDatabase(databaseName);
	        }
	        // Set the URL for the existing or newly created database
	        String dbUrl = prop.getProperty("DB_URL") + "/" + databaseName; // Correct concatenation
	        con = DriverManager.getConnection(dbUrl, prop.getProperty("DB_USERNAME"), prop.getProperty("DB_PASSWORD"));
	        
	        if (!tableExists(con, tableName1) || !tableExists(con, tableName2) || !tableExists(con, tableName3) || !tableExists(con, tableName4) || !tableExists(con, tableName5)) {
	            // Create tables if they do not exist
//	        	System.out.println("TABLES CREATING...");
	            createTables(con);
//	            System.out.println("SUCCESSFULLY CREATED TABLES.");
	        }
	        
//	        System.out.println(!triggerExists(con, triggerName));
	        if (!triggerExists(con, triggerName)) {
//	        	Create trigger if they do not exist
	        	createTrigger(con);
            }
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return con;
	}

	
	private static boolean databaseExists(String databaseName) throws SQLException {
	    try (Connection connection = DriverManager.getConnection(prop.getProperty("DB_URL"), prop.getProperty("DB_USERNAME"), prop.getProperty("DB_PASSWORD"));
	         Statement statement = connection.createStatement()) {
	        ResultSet resultSet = statement.executeQuery("SHOW DATABASES LIKE '" + databaseName + "'");
	        return resultSet.next();
	    }
	}

	// Helper method to create a new database
	private static void createDatabase(String databaseName) throws SQLException {
	    try (Connection connection = DriverManager.getConnection(prop.getProperty("DB_URL"), prop.getProperty("DB_USERNAME"), prop.getProperty("DB_PASSWORD"));
	         Statement statement = connection.createStatement()) {
	        statement.executeUpdate("CREATE DATABASE " + databaseName);
	    }
	}
	// Helper method to check if a table exists
	private static boolean tableExists(Connection connection, String tableName) throws SQLException {
		DatabaseMetaData metaData = null;
	    ResultSet resultSet = null;
	    try { metaData = connection.getMetaData();
	          resultSet = metaData.getTables(null, null, tableName, null);
	        return resultSet.next();
	          }finally {
	        	  // Close the resources in a finally block to ensure they are closed even if an exception occurs
	        	  if (resultSet != null) {
	  	            resultSet.close();
	  	        	}
	        	  if (metaData != null) {
	  	            // Depending on the JDBC driver, you might not need to close DatabaseMetaData explicitly
	  	            // See the documentation of your specific JDBC driver
	  	            // metaData.close();
	        		  }
	          }
	    }

	// Helper method to create tables
	private static void createTables(Connection connection) throws SQLException {
	    try (Statement statement = connection.createStatement()) {
	        // Create tables as needed
	    	try {
	    		statement.executeUpdate("CREATE TABLE supplier(\r\n"
	    				+ "supplierId varchar(25) PRIMARY KEY,\r\n"
	    				+ "supplierName varchar(50) NOT NULL,\r\n"
	    				+ "contactPerson varchar(50),\r\n"
	    				+ "email varchar(50) UNIQUE,\r\n"
	    				+ "phone bigint UNIQUE\r\n"
	    				+ ")");	    		
	    	} catch(SQLException e) {
	    		
	    	}
	    	try {
	    		statement.executeUpdate("CREATE TABLE industrial_goods(\r\n"
	    				+ "industryId varchar(25) PRIMARY KEY,\r\n"
	    				+ "industry varchar(50) UNIQUE,\r\n"
	    				+ "industry_description varchar(255) \r\n"
	    				+ ")");	    		
	    	} catch(SQLException e) {
	    		
	    	}
	    	try {
	    		statement.executeUpdate("CREATE TABLE consumer_goods(\r\n"
	    				+ "consumerId varchar(25) PRIMARY KEY,\r\n"
	    				+ "consumerGoods varchar(50) UNIQUE,\r\n"
	    				+ "consumer_description varchar(255) \r\n"
	    				+ ")");	    		
	    	} catch(SQLException e) {
	    		
	    	}
	    	try {
	    		statement.executeUpdate("CREATE TABLE products(\r\n"
	    				+ "productId varchar(25) PRIMARY KEY, \r\n"
	    				+ "productName varchar(50) NOT NULL, \r\n"
	    				+ "productDescription varchar(255), \r\n"
	    				+ "unitPrice double DEFAULT 0\r\n"
	    				+ ")");
	    	} catch(SQLException e) {
	    		
	    	}
	    	
	    	try {
	    		statement.executeUpdate("CREATE TABLE products_information(\r\n"
	    				+ "productInfoId varchar(25) PRIMARY KEY,\r\n"
	    				+ "productId varchar(25),\r\n"
	    				+ "supplierId varchar(25),\r\n"
	    				+ "industryId varchar(25),\r\n"
	    				+ "consumerId varchar(25),\r\n"
	    				+ "CONSTRAINT FK_productId FOREIGN KEY (productId) REFERENCES products(productId) ON DELETE RESTRICT,\r\n"
	    				+ "CONSTRAINT FK_supplier FOREIGN KEY (supplierId) REFERENCES supplier(supplierId) ON DELETE RESTRICT,\r\n"
	    				+ "CONSTRAINT FK_industrialGoods FOREIGN KEY (industryId) REFERENCES industrial_goods(industryId) ON DELETE RESTRICT,\r\n"
	    				+ "CONSTRAINT FK_consumerGoods FOREIGN KEY (consumerId) REFERENCES consumer_goods(consumerId)ON DELETE RESTRICT\r\n"
	    				+ ")");	    		
	    	}catch(SQLException e) {
	    		
	    	}
	    	
	    	try {
	    		statement.executeUpdate("CREATE TABLE inventory(\r\n"
	    				+ "inventoryId varchar(25) PRIMARY KEY,\r\n"
	    				+ "productId varchar(25),\r\n"
	    				+ "quntityInStock bigint DEFAULT 0,\r\n"
	    				+ "lastStockUpdate datetime DEFAULT NOW(),\r\n"
	    				+ "CONSTRAINT Fk_inventory_productId FOREIGN KEY (productId) REFERENCES products(productId) ON DELETE RESTRICT\r\n"
	    				+ ")");	    		
	    	}catch(SQLException e) {
	    		
	    	}
	        }
	    }
	private static boolean triggerExists(Connection connection, String triggerName) throws SQLException {
        ResultSet resultSet = null;

        try {
//            DatabaseMetaData metaData = connection.getMetaData();
            String query = "SELECT * FROM information_schema.TRIGGERS WHERE TRIGGER_NAME = ? AND EVENT_OBJECT_SCHEMA = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, triggerName);
                statement.setString(2, connection.getCatalog()); // Use the current database as the schema

                resultSet = statement.executeQuery();
                return resultSet.next();
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
	
	private static void createTrigger(Connection connection) throws SQLException {
	    try (Statement statement = connection.createStatement()) {
	        try {
	            // Define the trigger without DELIMITER
	            String triggerSQL = 
	                "CREATE TRIGGER update_current_capacity " +
	                "AFTER UPDATE ON inventory " +
	                "FOR EACH ROW " +
	                "BEGIN " +
	                "    DECLARE deltaQuantity INT; " +
	                "    SET deltaQuantity = NEW.quntityInStock - OLD.quntityInStock; " +
	                "    UPDATE warehouse " +
	                "    SET currentCapacity = currentCapacity + deltaQuantity " +
	                "    WHERE warehouseId = (SELECT warehouseId FROM warehouse_storage WHERE inventoryId = NEW.inventoryId); " +
	                "END";

	            statement.executeUpdate(triggerSQL);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


	
	
	
	//Class main method
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		getConnection();
		System.out.println("create...");
	}

}
