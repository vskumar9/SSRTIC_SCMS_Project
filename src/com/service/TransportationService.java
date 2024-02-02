package com.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.exception.InvalidException;
import com.management.TransportationManagement;
import com.model.Transportation;
import com.util.ApplicationUtil;

public class TransportationService {

	TransportationManagement tm = new TransportationManagement();
	ApplicationUtil util = new ApplicationUtil();
	
//	Helper method to add shipment transport
	public String addShipment(String carrierId, String orders) {
		String shipmentId = generateUniqueShipmentId();
		try {
			if(tm.addTransport(shipmentId, carrierId, "Shipped")) {
				String[] order = orders.split("-");
				for(String ord:order) {
					if(!tm.isCheckingOrders(ord)) {
						tm.addShipment(shipmentId, ord);						
					}
					else {
						System.out.println("Order id: "+ord+" is already transported.");
					}
				}
				return "Transport Id: "+shipmentId;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
//	Helper method to update shipment transport
	public boolean updateShipment(String shipmentId, String status) {
		try {
			if(util.isValidShipment(shipmentId)) {				
				if(tm.updateShipment(shipmentId, status)){
					return true;
				}
				else {
					System.out.println("Shipment Id: "+shipmentId+" not existed.");
				}
			}
		} catch (ClassNotFoundException | InvalidException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
//	Helper method to view all transports
	public ArrayList<Transportation> viewShipment() {
		try {
			return tm.viewShipment();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
//	Helper method to search shipment by id
	public ArrayList<Transportation> searchShipmentById(String shipmentId) {
		try {
			if(util.isValidShipment(shipmentId)) {
				return tm.searchShipmentById(shipmentId);				
			}
		} catch (ClassNotFoundException | SQLException | InvalidException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
//	Helper method to search shipment by carrier id
	public ArrayList<Transportation> searchTransportByCarrierId(String carrierId){
		try {
			if(util.isValidCarrier(carrierId)) {
				return tm.searchTransportByCarrierId(carrierId);								
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
//	Helper method to search carriers by id
	public ArrayList<String> searchCarrierById(String carrierId){
		try {
			return tm.searchCarrierById(carrierId);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
//	Helper method to checking carrier is exists or not
	public boolean isCheckingCarrier(String carrierId) {
		try {
			if(util.isValidCarrier(carrierId)) {
				return tm.isCheckingCarrier(carrierId);
			}
		} catch (InvalidException | ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	// Helper method to generate Shipment unique id
	public String generateUniqueShipmentId() {
	       return "SHIP"+generateSCMId();
	}
	
	private String generateSCMId() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyMddHHmmSS");
	    String timestamp = dateFormat.format(new Date());
	    int randomSuffix = (int) (Math.random() * 1000); // Add a random suffix for uniqueness
	    return timestamp + String.format("%03d", randomSuffix);
	}

}
