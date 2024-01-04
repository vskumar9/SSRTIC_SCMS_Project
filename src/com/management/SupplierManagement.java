package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Supplier;

public class SupplierManagement {
	
	//Helper method to add new supplier details
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
	
	// Helper method to delete already exists supplier details
	public boolean deleteSupplier(String supplierId) throws ClassNotFoundException, SQLException {
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("DELETE FROM supplier WHERE LOWER(supplierId) = LOWER(?)");
			){
			st.setString(1, supplierId);
			
			return st.executeUpdate()>0;
		}
	}
	
	// Helper method to update already exists supplier details
	public boolean updateSupplier(Supplier supplier) throws ClassNotFoundException, SQLException	{
		
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("UPDATE supplier SET supplierName = ?, contactPerson = ?, email = ?, phone = ? WHERE LOWER(supplierId) = LOWER(?)");
			){
			
			st.setString(1, supplier.getSupplierName());
			st.setString(2, supplier.getContactPerson());
			st.setString(3, supplier.getEmail());
			st.setLong(4, supplier.getPhone());	
			st.setString(5,supplier.getSupplierId());
			
			return st.executeUpdate()>0;
			
		}
	}

	// Helper method to retrieve the all supplier details
	public ArrayList<Supplier> viewSuppliers() throws SQLException, ClassNotFoundException{
		
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
	
	// Helper method to retrieve the supplier details specific supplier id 
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
	
	// Helper method to retrieve the supplier details specific supplier name
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
	
	// Helper method to return boolean values opposite of already exists email id exists or not
	public boolean searchEmail(String email) throws SQLException, ClassNotFoundException {
		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM supplier WHERE LOWER(email) = LOWER(?)");				
			){
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			return !rs.next();
		}
	}

	// Helper method to return boolean values opposite of already exists phone number exists or not
	public boolean searchPhoneNumber(long phone) throws SQLException, ClassNotFoundException {
		try (
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM supplier WHERE phone = ?");				
			){
			st.setLong(1, phone);
			ResultSet rs = st.executeQuery();
			return !rs.next();
		}
	}
	

	
}
