package com.management;
import java.sql.*;
import java.util.*;
import java.io.*;

public class DBConnection {
	
	private static Connection con = null;
	private static Properties prop = new Properties();
	
	@SuppressWarnings("resource")
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			FileInputStream file = null;
			file = new FileInputStream("src/DB.properties");
			prop.load(file);
			Class.forName(prop.getProperty("DB_DRIVER_CLASS"));
			con = DriverManager.getConnection(prop.getProperty("DB_URL"), prop.getProperty("DB_USERNAME"), prop.getProperty("DB_PASSWORD"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return con;
	}	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		getConnection();
	}

}
