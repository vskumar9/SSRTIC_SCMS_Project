package com.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.exception.InvalidException;
import com.management.WarehouseManagement;
import com.model.Warehouse;
import com.util.ApplicationUtil;

public class WarehouseService {
	
	WarehouseManagement wm = new WarehouseManagement();
	ApplicationUtil util = new ApplicationUtil();

	public String addWarehouse(String warehouseDetails) {
		String[] warehouse = warehouseDetails.split(":");
		try {
			if(warehouse.length == 3) {
				if(!wm.checkingWarehouse(warehouse[0], warehouse[1])) {
					String warehouseId = generateUniqueId();
					wm.addWarehouse(new Warehouse(warehouseId, warehouse[0], warehouse[1], Integer.valueOf(warehouse[2])));
					return warehouse[0]+" Warehouse Id: "+warehouseId;
				}
				System.out.println("Already Exists Warehouse Details.");
				return null;
			}
			System.out.println("Invalid warehouse details.");			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean deleteWarehouse(String warehouseId) {
		try {
			if(util.validateWarehouseId(warehouseId)) {
				return wm.deleteWarehouse(warehouseId);
			}
			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateWarehouse(String warehouseDetails) {
		String[] warehouse = warehouseDetails.split(":");
		try {
			if(util.validateWarehouseId(warehouse[0]) && warehouse.length == 4) {
				return wm.updateWarehouse(new Warehouse(warehouse[0], warehouse[1], warehouse[2],Integer.valueOf(warehouse[3])));
			}
			System.out.println("Invalid warehouse details.");
			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public ArrayList<Warehouse> viewWarehouse(){
		try {
			return wm.viewWarehouse();
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Warehouse> searchWarehouseById(String warehouseId){
		try {
			return wm.searchWarehouseById(warehouseId);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Warehouse> searchWarehouseByName(String warehouseName){
		try {
			return wm.searchWarehouseByName(warehouseName);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to generate warehouse unique id
		public String generateUniqueId() {
		       return "WRHS"+generateSCMId();
		    }
		private String generateSCMId() {
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyMddHHmmSS");
	     String timestamp = dateFormat.format(new Date());
		    int randomSuffix = (int) (Math.random() * 1000); // Add a random suffix for uniqueness
		    return timestamp + String.format("%03d", randomSuffix);
		    }


}
