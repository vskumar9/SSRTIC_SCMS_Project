package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Supplier;

public class SupplierManagement {
	
	public boolean addSupplier(Supplier supplierDetails) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("INSERT INTO supplier VALUES(?, ?, ?, ?, ?)");
			){
			st.setString(1, supplierDetails.getSupplierId());
			st.setString(2, supplierDetails.getSupplierName());
			st.setString(3, supplierDetails.getContactPerson());
			st.setString(4, supplierDetails.getEmail());
			st.setLong(5, supplierDetails.getPhone());	
			
			return st.executeUpdate()>0;
		}
	}
	
	public boolean deleteSupplier(String supplierId) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("DELETE FROM supplier WHERE LOWER(supplierId) = LOWER(?)");
			){
			st.setString(1, supplierId);
			
			return st.executeUpdate()>0;
		}
	}
		
	public boolean updateSupplier(String supplierId, String supplierName, String contactPerson, String email, long phone ) throws ClassNotFoundException, SQLException	{
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("UPDATE supplier SET supplierName = ?, contactPerson = ?, email = ?, phone = ? WHERE LOWER(supplierId) = LOWER(?)");
			){
			
			st.setString(1,supplierName);
			st.setString(2, contactPerson);
			st.setString(3, email);
			st.setLong(4, phone);
			st.setString(5, supplierId);	
			
			return st.executeUpdate()>0;
			
		}
	}

	public ArrayList<Supplier> searchSupplier() throws SQLException, ClassNotFoundException{
		
		ArrayList<Supplier> list = new ArrayList<Supplier>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM supplier");				
			){
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				list.add(new Supplier(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5)));
			}	
		}
		return list;
	}
	
	public ArrayList<Supplier> searchSupplierById(String supplierId) throws SQLException, ClassNotFoundException{
		
		ArrayList<Supplier> list = new ArrayList<Supplier>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM supplier WHERE LOWER(supplierId) = LOWER(?)");				
			){
			st.setString(1, supplierId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				list.add(new Supplier(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5)));
			}	
		}
		return list;
	}
	
	public ArrayList<Supplier> searchSupplierByName(String supplierName) throws SQLException, ClassNotFoundException{
		
		ArrayList<Supplier> list = new ArrayList<Supplier>();
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM supplier WHERE LOWER(supplierName) = LOWER(?)");				
			){
			st.setString(1, supplierName);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				list.add(new Supplier(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5)));
			}	
		}
		return list;
	}
	
}
