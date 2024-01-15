package com.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.exception.InvalidException;
import com.management.InventoryManagement;
import com.management.ProductManagement;
import com.management.WarehouseManagement;
import com.model.Inventory;
import com.util.ApplicationUtil;

public class InventoryService {

	ProductManagement pm = new ProductManagement();
	InventoryManagement im = new InventoryManagement();
	ApplicationUtil util = new ApplicationUtil();
	WarehouseManagement wm = new WarehouseManagement();
	
	public String addInventory(String inventoryDetails) {
		String[] inventory = inventoryDetails.split(":");
		try {
			if(pm.checkingProductId(inventory[0])) {
				if(inventory.length == 2) {
//					if(!im.checkingInventory(inventory[0])) {
						String inventoryId = generateUniqueId();
						Inventory in = new Inventory(inventoryId, inventory[0], Long.valueOf(inventory[1]), new Timestamp(0));
						if(im.addInventory(in))
							return "Inventory Id: "+inventoryId;						
//					}
//					else {
//						System.out.println("Already exists product id: "+inventory[0]);
//						return null;
//					}				
				}
				else {
					System.out.println("Invalid Inventory Data.");
					return null;
				}
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteInventory(String inventoryId) {
		try {
			
			if(util.validateInventoryId(inventoryId) && !wm.existsInventoryInWarehouse_storage(inventoryId)) {
				return im.deleteInventory(inventoryId);
			}
			System.out.println("This inventory doesn't delete. This is stored in the warehouse.");
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateInventory(String inventoryDetails) {
		
		String[] inventory = inventoryDetails.split(":");
		
		try {
			
			if(inventory.length == 3 && util.validateInventoryId(inventory[0])) {
				if(pm.checkingProductId(inventory[1])) {
					return im.updateInventory(new Inventory(inventory[0], inventory[1], Long.valueOf(inventory[2]), new Timestamp(0)));
					
				} else {
					System.out.println("Product not exists.");
					return false;
				}	
			}			
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public ArrayList<Inventory> viewInventory(){
		try {
			return im.viewInventory();
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Inventory> searchInventoryById(String inventoryId){
		try {
			if(util.validateInventoryId(inventoryId)) {
				return im.searchInventoryById(inventoryId);				
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Inventory> searchInventoryByProductId(String productId){
		try {
			if(util.validateProductId(productId)) {
				return im.searchInventoryByProductId(productId);				
			}
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ArrayList<Inventory> searchInventoryByProductName(String productName){
		try {
			return im.searchInventoryByProductName(productName);
		} catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Helper method to generate inventory unique id
		public String generateUniqueId() {
		       return "INVT"+generateSCMId();
		    }
		private String generateSCMId() {
	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyMddHHmmSS");
	     String timestamp = dateFormat.format(new Date());
		    int randomSuffix = (int) (Math.random() * 1000); // Add a random suffix for uniqueness
		    return timestamp + String.format("%03d", randomSuffix);
			}

}
