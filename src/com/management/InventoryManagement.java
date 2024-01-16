package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.model.Inventory;

public class InventoryManagement {

	public boolean addInventory(Inventory inventory) throws ClassNotFoundException, SQLException {
	    try (
	        Connection con = DBConnection.getConnection();
	        PreparedStatement st = con.prepareStatement("INSERT INTO inventory (inventoryId, productId, quntityInStock, lastStockUpdate) VALUES (?, ?, ?, ?)");
	    ) {
	    	
	        st.setString(1, inventory.getInventoryId());
	        st.setString(2, inventory.getProductId());
	        st.setLong(3, inventory.getQuantityInStock());
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            st.setTimestamp(4, timestamp);


	        return st.executeUpdate() > 0;
	    }
	}

		
		public boolean deleteInventory(String inventoryId) throws ClassNotFoundException, SQLException {
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("DELETE FROM inventory WHERE LOWER(inventoryId) = LOWER(?)");
				){
				
				st.setString(1, inventoryId);
				
				return st.executeUpdate()>0;
				
			}
		}
		
		public boolean updateInventory(Inventory inventory) throws ClassNotFoundException, SQLException {
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("UPDATE inventory SET productId = ?, quntityInStock = ?, lastStockUpdate = ? WHERE LOWER(inventoryId) = LOWER(?)");
				) {
				
				st.setString(1, inventory.getProductId());
				st.setLong(2, inventory.getQuantityInStock());
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	            st.setTimestamp(3, timestamp);
				st.setString(4, inventory.getInventoryId());
				
				return st.executeUpdate()>0;
					
			}
		}
		
		public ArrayList<Inventory> viewInventory() throws ClassNotFoundException, SQLException{
			ArrayList<Inventory> list = new ArrayList<Inventory>();
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT\r\n"
							+ "    i.inventoryId,\r\n"
							+ "    i.productId,\r\n"
							+ "    p.productName,\r\n"
							+ "    i.quntityInStock,\r\n"
							+ "    i.lastStockUpdate\r\n"
							+ "FROM\r\n"
							+ "    inventory i\r\n"
							+ "JOIN\r\n"
							+ "    products p ON i.productId = p.productId\r\n"
							+ "");
				){
				
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					list.add(new Inventory(rs.getString("inventoryId"), rs.getString("productId")+" | "+rs.getString("productName"), rs.getLong("quntityInStock"), rs.getTimestamp("lastStockUpdate")));
				}
				return list;
			}
		}
		
		public ArrayList<Inventory> searchInventoryById(String inventoryId) throws ClassNotFoundException, SQLException{
			
			ArrayList<Inventory> list = new ArrayList<Inventory>();
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT\r\n"
							+ "    i.inventoryId,\r\n"
							+ "    i.productId,\r\n"
							+ "    p.productName,\r\n"
							+ "    i.quntityInStock,\r\n"
							+ "    i.lastStockUpdate\r\n"
							+ "FROM\r\n"
							+ "    inventory i\r\n"
							+ "JOIN\r\n"
							+ "    products p ON i.productId = p.productId\r\n"
							+ "    WHERE \r\n"
							+ "    LOWER(i.inventoryId) = LOWER(?)\r\n"
							+ "");
				){
				
				st.setString(1, inventoryId);
				
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					list.add(new Inventory(rs.getString("inventoryId"), rs.getString("productId")+" | "+rs.getString("productName"), rs.getLong("quntityInStock"), rs.getTimestamp("lastStockUpdate")));
				}
				return list;
			}
			
		}
		
		public ArrayList<Inventory> searchInventoryByProductId(String productId) throws ClassNotFoundException, SQLException{
			
			ArrayList<Inventory> list = new ArrayList<Inventory>();
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT\r\n"
							+ "    i.inventoryId,\r\n"
							+ "    i.productId,\r\n"
							+ "    p.productName,\r\n"
							+ "    i.quntityInStock,\r\n"
							+ "    i.lastStockUpdate\r\n"
							+ "FROM\r\n"
							+ "    inventory i\r\n"
							+ "JOIN\r\n"
							+ "    products p ON i.productId = p.productId\r\n"
							+ "    WHERE \r\n"
							+ "    LOWER(p.productId) = LOWER(?)\r\n"
							+ "");
				){
				
				st.setString(1, productId);
				
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					list.add(new Inventory(rs.getString("inventoryId"), rs.getString("productId")+" | "+rs.getString("productName"), rs.getLong("quntityInStock"), rs.getTimestamp("lastStockUpdate")));
				}
				return list;
			}
			
		}
		
		public ArrayList<Inventory> searchInventoryByProductName(String productName) throws ClassNotFoundException, SQLException{
			
			ArrayList<Inventory> list = new ArrayList<Inventory>();
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT\r\n"
							+ "    i.inventoryId,\r\n"
							+ "    i.productId,\r\n"
							+ "    p.productName,\r\n"
							+ "    i.quntityInStock,\r\n"
							+ "    i.lastStockUpdate\r\n"
							+ "FROM\r\n"
							+ "    inventory i\r\n"
							+ "JOIN\r\n"
							+ "    products p ON i.productId = p.productId\r\n"
							+ "    WHERE \r\n"
							+ "    LOWER(p.productName) = LOWER(?)\r\n"
							+ "");
				){
				
				st.setString(1, productName);
				
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					list.add(new Inventory(rs.getString("inventoryId"), rs.getString("productId")+" | "+rs.getString("productName"), rs.getLong("quntityInStock"), rs.getTimestamp("lastStockUpdate")));
				}
				return list;
			}
			
		}
		
		public boolean checkingInventory(String productId) throws SQLException, ClassNotFoundException {
			try(
					Connection con = DBConnection.getConnection();
					PreparedStatement st = con.prepareStatement("SELECT * FROM inventory WHERE LOWER(productId) = LOWER(?)");
				){
				
				st.setString(1, productId);
				ResultSet rs = st.executeQuery();
				
				return rs.next();
				
			}
		}
	
	

}
