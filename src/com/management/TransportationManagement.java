package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Transportation;

public class TransportationManagement {

	public boolean addTransport(String shipmentId, String carrierId, String status) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("INSERT INTO transport VALUES(?, ?, ?)");
			){
			
			st.setString(1, shipmentId);
			st.setString(2, carrierId);
			st.setString(3, status);
			
			return st.executeUpdate()>0;
			
		}
	}
	
	public boolean addShipment(String shipmentId, String order) throws SQLException, ClassNotFoundException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("INSERT INTO shipment VALUES(?, ?)");
			){
			
			st.setString(1, shipmentId);
			st.setString(2, order);
			
			return st.executeUpdate()>0;
			
		}
	}

	
	public boolean updateShipment(String shipmentId, String status) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("UPDATE transport SET shipmentStatus = ? WHERE LOWER(shipmentId) = LOWER(?)");
			){
			
			st.setString(1, status);
			st.setString(2, shipmentId);
			
			return st.executeUpdate()>0;
			
		}
	}
	
	public ArrayList<Transportation> viewShipment() {
		ArrayList<Transportation> list = new ArrayList<Transportation>();
		return null;
	}
	
	public ArrayList<Transportation> searchShipmentById() {
		ArrayList<Transportation> list = new ArrayList<Transportation>();
		return null;
	}
	
//	Helper method to checking carrier is exists or not
	public boolean isCheckingCarrier(String carrierId) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM carriers WHERE LOWER(carrierID) = LOWER(?)");
			){
			st.setString(1, carrierId);
			
			return st.executeQuery().next();
			
		}
	}
	
	public boolean isCheckingOrders(String order) throws ClassNotFoundException, SQLException {
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM shipment WHERE LOWER(orderId) = LOWER(?)");
			){
			st.setString(1, order);
			
			return st.executeQuery().next();
			
		}
	}

}
