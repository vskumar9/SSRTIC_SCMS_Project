package com.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.exception.InvalidException;
import com.management.InventoryManagement;
import com.management.WarehouseManagement;
import com.model.Inventory;
import com.model.Warehouse;
import com.util.ApplicationUtil;

public class WarehouseService {
	
	// Create objects. it's help's to accessing class inside methods
	WarehouseManagement wm = new WarehouseManagement();
	ApplicationUtil util = new ApplicationUtil();
	InventoryManagement im = new InventoryManagement();

	// Helper method to add new Warehouse
	public String addWarehouse(String warehouseDetails) {
		String[] warehouse = warehouseDetails.split(":");
		try {
			if(warehouse.length == 3) {
				if(!wm.checkingWarehouse(warehouse[0], warehouse[1])) {
					String warehouseId = generateUniqueId();
					if(wm.addWarehouse(new Warehouse(warehouseId, warehouse[0], warehouse[1], Integer.valueOf(warehouse[2]))))
						return warehouse[0]+" Warehouse Id: "+warehouseId;
					return null;
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
	
	// Helper method to Delete Warehouse
	public boolean deleteWarehouse(String warehouseId) {
		try {
			if(util.validateWarehouseId(warehouseId) && !wm.existsWarehouseInWarehouse_storage(warehouseId)) {
				return wm.deleteWarehouse(warehouseId);
			}
			System.out.println("Warehouse doesn't delete. In the warehouse inventories are stored.");
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	// Helper method to Update Warehouse
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
	
	// Helper method to Retrieve All Warehouses
	public ArrayList<Warehouse> viewWarehouse(){
		try {
			return wm.viewWarehouse();
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to Retrieve Warehouse specific Id
	public ArrayList<Warehouse> searchWarehouseById(String warehouseId){
		try {
			return wm.searchWarehouseById(warehouseId);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to Retrieve Warehouse Specific Name
	public ArrayList<Warehouse> searchWarehouseByName(String warehouseName){
		try {
			return wm.searchWarehouseByName(warehouseName);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to add new Inventory in Specific Warehouse
	public boolean addInventory(String warehouseId, String inventoryId) {
		try {
			if(util.validateInventoryId(inventoryId) && im.searchInventoryById(inventoryId) != null) {
				if(!wm.checkingInventory(warehouseId, inventoryId))
						return wm.addInventory(warehouseId, inventoryId);
				System.out.println("Already Exist Inventory in Warehouse.");
				return false;
			}
			System.out.println("Invalid inventory id or not exists invenotry: "+inventoryId);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	// Helper method to Delete Inventory in Specific Warehouse
	public boolean deleteInventory(String warehouseId, String inventoryId) {
		try {
			if(util.validateInventoryId(inventoryId) && im.searchInventoryById(inventoryId) != null) {
				return wm.deleteInventory(warehouseId, inventoryId);				
			}
			System.out.println("Invalid inventory id or not exists invenotry: "+inventoryId);
			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	// Helper method to Retrieve all Inventories Specific Warehouse
	public ArrayList<Inventory> viewInventoryDetails(String warehouseId) {
		try {
			if(util.validateWarehouseId(warehouseId)) {
				return wm.viewInventoryDetails(warehouseId);				
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
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
