package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Transportation;

public class TransportationManagement {

//	Helper method to add transport
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
	
//	Helper method to add shipment
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

	
//	Helper method to update shipment transport
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
	
//	Helper method to view all transports
	public ArrayList<Transportation> viewShipment() throws ClassNotFoundException, SQLException {
		ArrayList<Transportation> list = new ArrayList<Transportation>();
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT t.shipmentId, t.carrierID, t.shipmentStatus, s.orderId FROM transport t JOIN shipment s ON t.shipmentId = s.shipmentId")
			){
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				list.add(new Transportation(rs.getString("shipmentId"), rs.getString("orderId"), rs.getString("carrierID"), rs.getString("shipmentStatus")));
			}
			
			return list;
			
		}
	}
	
//	Helper method to search shipment by id
	public ArrayList<Transportation> searchShipmentById(String shipmentId) throws ClassNotFoundException, SQLException {
		ArrayList<Transportation> list = new ArrayList<Transportation>();
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT t.shipmentId, t.carrierID, t.shipmentStatus, s.orderId FROM transport t JOIN shipment s ON t.shipmentId = s.shipmentId WHERE LOWER(t.shipmentId) = LOWER(?)")
			){
			
			st.setString(1, shipmentId);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				list.add(new Transportation(rs.getString("shipmentId"), rs.getString("orderId"), rs.getString("carrierID"), rs.getString("shipmentStatus")));
			}
			
			return list;
			
		}
	}
	
//	Helper method to search shipment by carrier id
	public ArrayList<Transportation> searchTransportByCarrierId(String carrierId) throws ClassNotFoundException, SQLException {
		ArrayList<Transportation> list = new ArrayList<Transportation>();
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT t.shipmentId, t.carrierID, t.shipmentStatus, s.orderId FROM transport t JOIN shipment s ON t.shipmentId = s.shipmentId WHERE LOWER(t.carrierID) = LOWER(?)")
			){
			
			st.setString(1, carrierId);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				list.add(new Transportation(rs.getString("shipmentId"), rs.getString("orderId"), rs.getString("carrierID"), rs.getString("shipmentStatus")));
			}
			
			return list;
			
		}
	}
	
//	Helper method to search carriers by id
	public ArrayList<String> searchCarrierById(String carrierId) throws  SQLException, ClassNotFoundException{
		ArrayList<String> list = new ArrayList<String>();
		try(
				Connection con = DBConnection.getConnection();
				PreparedStatement st = con.prepareStatement("SELECT * FROM carriers WHERE LOWER(carrierID) = LOWER(?)");
			){
			
			st.setString(1, carrierId);
			
			ResultSet rs = st.executeQuery();
			String carrierDetails = "%-30s%-30s%-30s%-30s%-30s";
			while(rs.next()) {
				list.add(String.format(carrierDetails, rs.getString("carrierID"), rs.getString("carrierName"), rs.getString("contactPerson"), rs.getString("contactEmail"), rs.getString("contactPhone")));
			}
			
			return list;
			
		}
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
	
//	Helper method to checking order by id
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
