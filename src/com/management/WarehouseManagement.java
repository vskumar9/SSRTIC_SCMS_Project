package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				PreparedStatement st = con.prepareStatement("UPDATE warehouse SET warehouseName = ?, location = ?, capacity = ? WHERE LOWER(warehouseId) = LOWER(?)");
			){
			
			st.setString(1, warehouse.getWarehouseName());
			st.setString(2, warehouse.getLocation());
			st.setInt(3, warehouse.getCapacity());
			st.setString(4, warehouse.getWarehouseId());
			
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
	
	
	

}
