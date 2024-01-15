package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Inventory;
import com.model.Warehouse;

public class WarehouseManagement {

	public boolean addWarehouse(Warehouse warehouse) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("INSERT INTO warehouse VALUES(?, ?, ?, ?, ?)");
			){
			
			st.setString(1, warehouse.getWarehouseId());
			st.setString(2, warehouse.getWarehouseName());
			st.setString(3, warehouse.getLocation());
			st.setInt(4, warehouse.getCapacity());
			st.setInt(5, warehouse.getCurrentCapacity());
			
			return st.executeUpdate()>0;
		}
	}
	
	public boolean deleteWarehouse(String warehouseId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("DELETE FROM warehouse WHERE LOWER(warehouseId) = LOWER(?)");
			){
			
			st.setString(1, warehouseId);
			
			return st.executeUpdate()>0;
		}
	}
	
	public boolean updateWarehouse(Warehouse warehouse) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("UPDATE warehouse SET warehouseName = ?, location = ?, capacity = ? WHERE LOWER(warehouseId) = LOWER(?) AND currentCapacity < ?");
			){
			
			st.setString(1, warehouse.getWarehouseName());
			st.setString(2, warehouse.getLocation());
			st.setInt(3, warehouse.getCapacity());
			st.setString(4, warehouse.getWarehouseId());
			st.setInt(5, warehouse.getCapacity());

			
			return st.executeUpdate()>0;
		}
	}
	
	public ArrayList<Warehouse> viewWarehouse() throws ClassNotFoundException, SQLException {
		ArrayList<Warehouse> list = new ArrayList<Warehouse>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM warehouse");
			){
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Warehouse wh = new Warehouse(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				wh.setCurrentCapacity(rs.getInt(5));
				
				list.add(wh);
			}
			return list;
		}
	}
	
	public ArrayList<Warehouse> searchWarehouseById(String warehouseId) throws ClassNotFoundException, SQLException{
		ArrayList<Warehouse> list = new ArrayList<Warehouse>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM warehouse WHERE LOWER(warehouseId) = LOWER(?)")
			){
			
			st.setString(1, warehouseId);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Warehouse wh = new Warehouse(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				wh.setCurrentCapacity(rs.getInt(5));
				
				list.add(wh);
			}
			return list;
		}
	}
	
	public ArrayList<Warehouse> searchWarehouseByName(String warehouseName) throws ClassNotFoundException, SQLException{
		ArrayList<Warehouse> list = new ArrayList<Warehouse>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM warehouse WHERE LOWER(warehouseName) LIKE  LOWER(?)")
			){
			
			st.setString(1,  "%"+warehouseName+"%");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Warehouse wh = new Warehouse(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				wh.setCurrentCapacity(rs.getInt(5));
				
				list.add(wh);
			}
			return list;
		}
	}
	
	public boolean addInventory(String warehouseId, String inventoryId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement capacityCheckStmt = con.prepareStatement("SELECT capacity, currentCapacity FROM warehouse WHERE warehouseId = ?");
				PreparedStatement Inv = con.prepareStatement("SELECT quntityInStock FROM inventory WHERE LOWER(inventoryId) = LOWER(?)");
				PreparedStatement st = con.prepareStatement("INSERT INTO warehouse_storage VALUES(?, ?)");
				PreparedStatement up = con.prepareStatement("UPDATE warehouse SET currentCapacity = currentCapacity + (SELECT quntityInStock FROM inventory WHERE inventoryId = ?) WHERE warehouseId = ?");
			){
			
			Inv.setString(1, inventoryId);

			ResultSet rs_inv = Inv.executeQuery();
			
			if(rs_inv.next()) {
				long InventoryCapacity = rs_inv.getLong("quntityInStock");
				
				capacityCheckStmt.setString(1, warehouseId);
				ResultSet rs_capacity = capacityCheckStmt.executeQuery();
				if(rs_capacity.next()) {
					long totalCapacity = rs_capacity.getLong("capacity");
					long currentCapacity = rs_capacity.getLong("currentCapacity");
					
					long availableCapacity = totalCapacity - currentCapacity;
					
					if (availableCapacity >= InventoryCapacity) {
						st.setString(1, warehouseId);
						st.setString(2, inventoryId);
						
						if(st.executeUpdate()>0) {
							up.setString(1, inventoryId);
							up.setString(2, warehouseId);
							return up.executeUpdate()>0;				
						}									
					}
				}
			}
			return false;
			
		}
	}
	
	public boolean deleteInventory(String warehouseId, String inventoryId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("DELETE FROM warehouse_storage WHERE LOWER(warehouseId) = LOWER(?) AND LOWER(inventoryId) = LOWER(?)");
				PreparedStatement up = con.prepareStatement("UPDATE warehouse SET currentCapacity = currentCapacity - (SELECT quntityInStock FROM inventory WHERE LOWER(inventoryId) = LOWER(?)) WHERE LOWER(warehouseId) = LOWER(?)");
			){
			
			st.setString(1, warehouseId);
			st.setString(2, inventoryId);
			
			if(st.executeUpdate()>0) {
				up.setString(1, inventoryId);
				up.setString(2, warehouseId);
				return up.executeUpdate()>0;				
			}
			
			return false;
			
		}
	}
	
	public ArrayList<Inventory> viewInventoryDetails(String warehouseId) throws ClassNotFoundException, SQLException{
		
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("select inventoryId, productId, productName, unitPrice, quntityInStock, lastStockUpdate from warehouse_storage natural join warehouse natural join inventory natural join products where LOWER(warehouseId) = LOWER(?)");
			){
			
			st.setString(1, warehouseId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				list.add(new Inventory(rs.getString("inventoryId"), rs.getString("productId")+" | "+rs.getString("productName")+" | "+rs.getInt("unitPrice"), rs.getLong("quntityInStock"), rs.getTimestamp("lastStockUpdate")));
			}
			return list;
		}
		
		
	}
	
	public boolean checkingWarehouse(String warehouseName, String location) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM warehouse WHERE LOWER(warehouseName) = LOWER(?) AND LOWER(location) = LOWER(?)");
			){
			
			st.setString(1, warehouseName);
			st.setString(2, location);
			
			return st.executeQuery().next();
			
		} 
	}
	
	public boolean checkingInventory(String warehouseId, String inventoryId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM warehouse_storage WHERE LOWER(warehouseId) = LOWER(?) AND LOWER(inventoryId) = LOWER(?)");
			){
			
			st.setString(1, warehouseId);
			st.setString(2, inventoryId);
			
			return st.executeQuery().next();
		}
	}
	
	public boolean existsInventoryInWarehouse_storage(String inventoryId) throws SQLException, ClassNotFoundException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM warehouse_storage WHERE LOWER(inventoryId) = LOWER(?)");
			){
			
			st.setString(1, inventoryId);
			
			return st.executeQuery().next();
		}
	}
	
	public boolean existsWarehouseInWarehouse_storage(String warehouseId) throws SQLException, ClassNotFoundException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM warehouse_storage WHERE LOWER(warehouseId) = LOWER(?)");
			){
			
			st.setString(1, warehouseId);
			
			return st.executeQuery().next();
		}
	}

}
