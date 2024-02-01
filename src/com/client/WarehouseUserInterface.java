package com.client;

import java.util.Scanner;

import com.exception.InvalidException;
import com.service.WarehouseService;
import com.util.ApplicationUtil;

public class WarehouseUserInterface {
	
	// Create objects. it's help's to accessing class inside methods
	Scanner sc = new Scanner(System.in);
	WarehouseService service = new WarehouseService();
	ApplicationUtil util = new ApplicationUtil();
	
	// Helper method to Warehouse section user Console interface method
	public void warehouseSection() {
		
		try {
			char warehouseChoice;
			do {
				System.out.print("--------------WAREHOUSE SECTION--------------\nA. Warehouses Management\nB. warehouse Storage Management\nC. <- Go to Main Section");
				System.out.print("\nEnter your option: ");
				
				warehouseChoice = sc.next().charAt(0);				
				
				switch(warehouseChoice) {
				case 'A':
				case 'a':
					// call Warehouse Management method
					warehouseManagement();
					break;
				case 'B':
				case 'b':
					// call Warehouse Storage Management method
					warehouseStorageManagement();
					break;
				case 'C':
				case 'c':
					// print string and exits the Warehouse method this choice
					System.out.println("-----Complete Warehouse Section-----");
					return;
				default:
					// print the select wrong choice
					System.out.println("Your choice is wrong.");
					
				}
				
			}while(warehouseChoice != 'C' || warehouseChoice != 'c');
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			warehouseSection();
		}
		
	}
	
	// Helper method to Warehouse Management Console User Interface
	public void warehouseManagement() {
		
		try {
			char warehouseChoice;
			do {
				System.out.print("--------------WAREHOUSE SECTION--------------\nA. Add warehouse\nB. Delete warehouse\nC. Update warehouse\nD. Show warehouse\nE. Search Warehouse\nF. <- Go to Main Section");
				System.out.print("\nEnter your option: ");
				
				warehouseChoice = sc.next().charAt(0);				
				
				switch(warehouseChoice) {
				case 'A':
				case 'a':
					// call addWarehouse method
					addWarehouse();
					break;
				case 'B':
				case 'b':
					// call deleteWarehouse method
					deleteWarehouse();
					break;
				case 'C':
				case 'c':
					// call updateWarehouse method
					updateWarehouse();
					break;
				case 'D':
				case 'd':
					// call displayWarehouse method
					displayWarehouse();
					break;
				case 'E':
				case 'e':
					// call searchWarehouse method
					searchWarehouse();
					break;
				case 'F':
				case 'f':
					// print string and exits the Warehouse method this choice
					System.out.println("-----Complete Warehouse Section-----");
					return;
				default:
					// print the select wrong choice
					System.out.println("Your choice is wrong.");
					
				}
				
			}while(warehouseChoice != 'F' || warehouseChoice != 'f');
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			warehouseManagement();
		}
	}
	
	// Helper method to Warehouse Storage Management Console User Interface
	private void warehouseStorageManagement() {
		
		try {
			char warehouseChoice;
			do {
				System.out.print("--------------WAREHOUSE SECTION--------------\nA. Add Inventory\nB. Delete Inventory\nC. Inventory Management\nD. Display Warehouse inventories\nE. <- Go to Main Section");
				System.out.print("\nEnter your option: ");
				
				warehouseChoice = sc.next().charAt(0);
				sc.nextLine();
				
				switch(warehouseChoice) {
				case 'A':
				case 'a':
					// call addInventory method
					addInventory();
					break;
				case 'B':
				case 'b':
					// call deleteInventory method
					deleteInventory();
					break;
				case 'C':
				case 'c':
					// call to InventoryUserInterface inventorySection method
					new InventoryUserInterface().inventorySection();
					break;
				case 'D':
				case 'd':
					// call viewWarehouseInventory method
					viewWarehouseInventory();
					break;
				case 'E':
				case 'e':
					// print string and exits the Warehouse method this choice
					System.out.println("-----Complete Warehouse Section-----");
					return;
				default:
					// print the select wrong choice
					System.out.println("Your choice is wrong.");
					
				}
				
			}while(warehouseChoice != 'E' || warehouseChoice != 'f');
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			warehouseStorageManagement();
		}
		
	}
	// Helper method to Retrieve all Inventoies Specific warehouse
	private void viewWarehouseInventory() {
		
		try {
			System.out.println("Enter warehouse Id: ");
			String warehouseId = sc.nextLine();
			if(!util.validateWarehouseId(warehouseId)&& service.searchWarehouseById(warehouseId)== null) {
				System.out.println("Invalid warehouse id: "+warehouseId);
				return;
			}
			System.out.println("--------------DISPLAY WAREHOUSE INVENTORY DETAILS--------------");
			System.out.println();
			System.out.printf("%-25s%-30s%-30s%-30s%-30s%-30s","Warehouse ID", "Warehouse Name", "Location", "Total Capacity", "Current Capacity", "Available Capacity");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			service.searchWarehouseById(warehouseId).forEach(e -> {
				System.out.println(e);
			});	
			System.out.println();
			System.out.println("--------------------------****************************Stock Inventory Details***************************--------------------------");
			System.out.println();
			System.out.printf("%-25s%-50s%-15s%-15s","Inventory ID", "Product information(ID, Name, Unit Price)", "quntityInStock", "lastStockUpdate");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			service.viewInventoryDetails(warehouseId).forEach(e -> {
				System.out.println(e);
			});
			System.out.println();			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
		} 
	}
	
	// Helper method to Delete Inventories specific warehouse
	private void deleteInventory() {
		try {
			
			System.out.println("--------------DELETE INVENTORY DETAILS IN WAREHOUSE--------------");
			System.out.println("Enter warehouse Id: ");
			String warehouseId = sc.nextLine();
			if(!util.validateWarehouseId(warehouseId)&& service.searchWarehouseById(warehouseId)== null) {
				System.out.println("Invalid warehouse id: "+warehouseId);
				return;
			}
			
			System.out.print("Enter number of invetories: ");
			int noOfInventory= sc.nextInt();
			sc.nextLine();
			while(noOfInventory<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfInventory = sc.nextInt();
				sc.nextLine();
			}
			System.out.println("Enter the inventory id : \n[INVENTORY_ID]");
			int success = 0;
			for(int i = 0; i < noOfInventory; i++) {
				String inventoryId;
				do {
					inventoryId = sc.nextLine();
				}while(inventoryId.isEmpty());
				if( service.deleteInventory(warehouseId, inventoryId)) {
					success++;
				}
				else
					System.out.println("inventory Id not exists.");
			}
			
			if(success==0) System.out.println("Inventories/Inventory not deleted....");
			else if(success==1) System.out.println("Successfully "+success+" Inventory deleted.");
			else if(success>1) System.out.println("Successfully "+success+" Inventories deleted.");
			
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			deleteInventory();
		}
	}
	
	// Helper method to Add New Inventory in Specific Warehouse
	private void addInventory() {
		try {
			
			System.out.println("--------------ADDING INVENTORY DETAILS IN WAREHOUSE--------------");
			System.out.println("Enter warehouse Id: ");
			String warehouseId = sc.nextLine();
			if(!util.validateWarehouseId(warehouseId)&& service.searchWarehouseById(warehouseId)== null) {
				System.out.println("Invalid warehouse id: "+warehouseId);
				return;
			}
			
			System.out.print("Enter number of invetories: ");
			int noOfInventory= sc.nextInt();
			sc.nextLine();
			while(noOfInventory<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfInventory = sc.nextInt();
				sc.nextLine();
			}
			System.out.println("Enter the inventory id : \n[INVENTORY_ID]");
			int success = 0;
			for(int i = 0; i < noOfInventory; i++) {
				String inventoryId;
				do {
					inventoryId = sc.nextLine();
				}while(inventoryId.isEmpty());
				if( service.addInventory(warehouseId, inventoryId)) {
					success++;
				}
			}
			
			if(success==0) System.out.println("Inventories/Inventory not added....");
			else if(success==1) System.out.println("Successfully "+success+" Inventory added.");
			else if(success>1) System.out.println("Successfully "+success+" Inventories added.");
			
		} catch(InvalidException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			addInventory();
		}
	}
	
	// Helper method to Search warehouse details specific Id or Name
	private void searchWarehouse() {
		try {
			int search;
			do {
				System.out.print("--------------SEARCH WAREHOUSE DETAILS--------------\n1. Warehouse ID\n2. Warehouse Name\n3. <- Go Back\nEnter your option: ");
				search = sc.nextInt();
				switch(search) {
				case 1: 
					System.out.print("Enter Warehouse Id: ");
					String warehouseId;
					do {
						warehouseId = sc.nextLine();
					}while(warehouseId.isEmpty());
					try {
						if(util.validateWarehouseId(warehouseId)) {
							if(service.searchWarehouseById(warehouseId) != null) {
								System.out.printf("%-25s%-30s%-30s%-30s%-30s%-30s","Warehouse ID", "Warehouse Name", "Location", "Total Capacity", "Current Capacity", "Available Capacity");
								System.out.println();
								System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
								service.searchWarehouseById(warehouseId).forEach(e -> {
									System.out.println(e);
								});														
							}
							else {
								System.out.println("Warehouse Id not exists.");
							}
						}
						System.out.println();
					}catch(InvalidException e) {
						System.out.println(e.getMessage());
					}
					break;
//				case 3:
//					System.out.print("Enter the Warehouse Name: ");
//					String warehouseName;
//					do {
//						warehouseName = sc.nextLine();
//					}while(warehouseName.isEmpty());
//					
//					if(service.searchWarehouseByName(warehouseName) == null) {
//						System.out.println("There is no WarehouseName on this name: "+warehouseName);
//					}
//					else {
//						System.out.printf("%-25s%-30s%-30s%-30s%-30s%-30s","Warehouse ID", "Warehouse Name", "Location", "Total Capacity", "Current Capacity", "Available Capacity");
//						System.out.println();
//						System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//						service.searchWarehouseByName(warehouseName).forEach(e->{
//							System.out.println(e);
//						});	
//					}
//					System.out.println();
//					break;
				case 2:
					System.out.println("Welcome Back Warehouse Section......!!!");
					return ;
				default:
					System.out.println("Please select correct option....");
				}
			}while(search != 3);
			} catch(Exception e) {
				System.out.println();
				System.out.println("Something error. Please try again.....");
				System.out.println();
				searchWarehouse();
			} 
		
	}

	// Helper method to Retrieve All Warehouse Details
	private void displayWarehouse() {
		
		try {
			System.out.println("--------------DISPLAY WAREHOUSE DETAILS--------------");
			System.out.printf("%-25s%-30s%-30s%-30s%-30s%-30s","Warehouse ID", "Warehouse Name", "Location", "Total Capacity", "Current Capacity", "Available Capacity");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			service.viewWarehouse().forEach(e -> {
				System.out.println(e);
			});
			System.out.println();			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
		} 
		
	}

	// Helper method to Update Warehouse Details
	private void updateWarehouse() {
		
		try {
			System.out.println("--------------UPDATE WAREHOUSE DETAILS--------------");
			System.out.print("Enter number of warehouses: ");
			int noOfWarehouses= sc.nextInt();
			sc.nextLine();
			while(noOfWarehouses<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfWarehouses = sc.nextInt();
				sc.nextLine();
			}
			System.out.println("Enter the warehouse details formate is : \n[WAREHOUSE_ID:WAREHOUSE_NAME:LOCATION:CAPACITY]");
			int success = 0;
			for(int i = 0; i < noOfWarehouses; i++) {
				String warehouseDetails;
				do {
					warehouseDetails = sc.nextLine();
				}while(warehouseDetails.isEmpty());
				if(service.updateWarehouse(warehouseDetails)) {
					success++;
				}
			}
			
			if(success==0) System.out.println("warehouses/warehouse not updated....");
			else if(success==1) System.out.println("Successfully "+success+" warehouse updated.");
			else if(success>1) System.out.println("Successfully "+success+" warehouses updated.");			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			updateWarehouse();
		} 
		
	}

	// Helper method to Delete Warehouse Details
	private void deleteWarehouse() {
		
		try {
			System.out.println("--------------DELETE WAREHOUSE DETAILS--------------");
			System.out.print("Enter number of warehouses: ");
			int noOfWarehouses = sc.nextInt();
			while(noOfWarehouses<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfWarehouses = sc.nextInt();
			}
			System.out.println("Enter the Warehouse Id's :");
			int success = 0;
			for(int i = 0; i < noOfWarehouses; i++) {
				String warehousesId;
				do {
					warehousesId = sc.nextLine();
				}while(warehousesId.isEmpty());
				if(service.deleteWarehouse(warehousesId)) {
					success++;
				}
				else
					System.out.println("Warehouse Id not exists.");
			}
			
			
			if(success == 0) System.out.println("warehouses/warehouse not deleted....");
			else if(success == 1) System.out.println("Successfully "+success+" warehouse deleted.");
			else if(success > 1) System.out.println("Successfully "+success+" warehouses deleted.");			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			deleteWarehouse();
		} 
		
	}

	// Helper method to Add New Warehouse
	private void addWarehouse() {
		
		try {
			System.out.println("--------------ADDING WAREHOUSE DETAILS--------------");
			System.out.print("Enter number of warehouses: ");
			int noOfWarehouses= sc.nextInt();
			sc.nextLine();
			while(noOfWarehouses<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfWarehouses = sc.nextInt();
				sc.nextLine();
			}
			System.out.println("Enter the warehouse details formate is : \n[WAREHOUSE_NAME:LOCATION:CAPACITY]");
			int success = 0;
			for(int i = 0; i < noOfWarehouses; i++) {
				String warehouseDetails;
				do {
					warehouseDetails = sc.nextLine();
				}while(warehouseDetails.isEmpty());
				String supplierId = service.addWarehouse(warehouseDetails);
				if( supplierId != null) {
					System.out.println(supplierId);
					success++;
				}
			}
			
			if(success==0) System.out.println("warehouses/warehouse not added....");
			else if(success==1) System.out.println("Successfully "+success+" warehouse added.");
			else if(success>1) System.out.println("Successfully "+success+" warehouses added.");			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			addWarehouse();
		}
		
	}

}
