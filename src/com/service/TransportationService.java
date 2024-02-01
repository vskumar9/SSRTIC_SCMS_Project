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
	
	public ArrayList<Transportation> viewShipment() {
		return null;
	}
	
	public ArrayList<Transportation> searchShipmentById(String shipmentId) {
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
