package com.client;

import java.util.Scanner;

import com.exception.InvalidException;
import com.service.WarehouseService;
import com.util.ApplicationUtil;

public class WarehouseUserInterface {
	
	Scanner sc = new Scanner(System.in);
	WarehouseService service = new WarehouseService();
	ApplicationUtil util = new ApplicationUtil();
	
	public void warehouseSection() {
		
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
			warehouseSection();
		}
	}
	
	
	
	
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
			System.out.println("Enter the warehouse details formate is : \n[INVENTORY_ID]");
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
			
			if(success==0) System.out.println("warehouses/warehouse not added....");
			else if(success==1) System.out.println("Successfully "+success+" warehouse added.");
			else if(success>1) System.out.println("Successfully "+success+" warehouses added.");
			
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
							System.out.printf("%-25s%-30s%-30s%-30s%-30s%-30s","Warehouse ID", "Warehouse Name", "Location", "Total Capacity", "Current Capacity", "Available Capacity");
							System.out.println();
							System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							service.searchWarehouseById(warehouseId).forEach(e -> {
								System.out.println(e);
							});						
						}
						System.out.println();
					}catch(InvalidException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.print("Enter the Warehouse Name: ");
					String warehouseName;
					do {
						warehouseName = sc.nextLine();
					}while(warehouseName.isEmpty());
					
					if(service.searchWarehouseByName(warehouseName) == null) {
						System.out.println("There is no WarehouseName on this name: "+warehouseName);
					}
					else {
						System.out.printf("%-25s%-30s%-30s%-30s%-30s%-30s","Warehouse ID", "Warehouse Name", "Location", "Total Capacity", "Current Capacity", "Available Capacity");
						System.out.println();
						System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						service.searchWarehouseByName(warehouseName).forEach(e->{
							System.out.println(e);
						});	
					}
					System.out.println();
					break;
				case 3:
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
