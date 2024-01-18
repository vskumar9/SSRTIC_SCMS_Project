package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.management.TransportationManagement;
import com.model.Transportation;
import com.util.ApplicationUtil;

public class TransportationService {

	TransportationManagement tm = new TransportationManagement();
	ApplicationUtil util = new ApplicationUtil();
	
	public String addShipment(String shipmentDetails) {
		return null;
	}
	
	public boolean deleteShipment(String shipmentId) {
		return false;
	}
	
	public boolean updateShipment(String shipmentDetails) {
		return false;
	}
	
	public ArrayList<Transportation> viewShipment() {
		return null;
	}
	
	public ArrayList<Transportation> searchShipmentById(String shipmentId) {
		return null;
	}
	
	
	// Helper method to generate Shipment unique id
	public String generateUniqueShipmentId() {
	       return "SHIP"+generateSCMId();
	}
		
	// Helper method to generate Carrier unique id
	public String generateUniqueCarrierId() {
		return "CARR"+generateSCMId();
	}
	private String generateSCMId() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyMddHHmmSS");
	    String timestamp = dateFormat.format(new Date());
	    int randomSuffix = (int) (Math.random() * 1000); // Add a random suffix for uniqueness
	    return timestamp + String.format("%03d", randomSuffix);
	}

}
