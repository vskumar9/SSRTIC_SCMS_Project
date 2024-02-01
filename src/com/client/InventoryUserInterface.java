package com.client;

import java.util.Scanner;

import com.exception.InvalidException;
import com.service.InventoryService;
import com.util.ApplicationUtil;

public class InventoryUserInterface {
	
	Scanner sc = new Scanner(System.in);
	InventoryService service = new InventoryService();
	ApplicationUtil util = new ApplicationUtil();
	
	public void inventorySection() {
		
		try {
			char inventoryChoice;
			do {
				System.out.print("--------------INVENTORY SECTION--------------\nA. Add Inventory\nB. Delete Inventory\nC. Update Inventory\nD. Show Inventory\nE. Search Inventory\nF. <- Go to Main Section");
				System.out.print("\nEnter your option: ");
				
				inventoryChoice = sc.next().charAt(0);				
				
				switch(inventoryChoice) {
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
					// call updateInventory method
					updateInventory();
					break;
				case 'D':
				case 'd':
					// call displayInventory method
					displayInventory();
					break;
				case 'E':
				case 'e':
					// call searchInventory method
					searchInventory();
					break;
				case 'F':
				case 'f':
					// print string and exits the Inventory method this choice
					System.out.println("-----Complete Inventory Section-----");
					return;
				default:
					// print the select wrong choice
					System.out.println("Your choice is wrong.");
					
				}
				
			}while(inventoryChoice != 'F' || inventoryChoice != 'f');
			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			inventorySection();
		}
		
	}
	
	private void searchInventory() {
		
		try {
			int search;
			do {
				System.out.print("--------------SEARCH INVENTORY DETAILS--------------\n1. Inventory ID\n2. Product Id\n3 <- Go Back\nEnter your option: ");
				search = sc.nextInt();
				switch(search) {
				case 1: 
					System.out.print("Enter Inventory Id: ");
					String inventoryId;
					do {
						inventoryId = sc.nextLine();
					}while(inventoryId.isEmpty());
					try {
						if(util.validateInventoryId(inventoryId)) {
							if(service.searchInventoryById(inventoryId) == null) {
								System.out.println("There is no inventory on this Id: "+inventoryId);
							}else {
								System.out.printf("%-25s%-50s%-15s%-15s","Inventory ID", "Product information", "quntityInStock", "lastStockUpdate");
								System.out.println();
								System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
								service.searchInventoryById(inventoryId).forEach(e -> {
									System.out.println(e);
								});														
							}
						}
						System.out.println();
					}catch(InvalidException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.print("Enter the Product Id: ");
					String productId;
					do {
						productId = sc.nextLine();
					}while(productId.isEmpty());
					
					if(service.searchInventoryByProductId(productId) == null) {
						System.out.println("There is no product on this Id: "+productId);
					}
					else {
						System.out.printf("%-25s%-50s%-15s%-15s","Inventory ID", "Product information", "quntityInStock", "lastStockUpdate");
						System.out.println();
						System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
						service.searchInventoryByProductId(productId).forEach(e->{
							System.out.println(e);
						});	
					}
					System.out.println();
					break;
//				case 4:
//					System.out.print("Enter the Product Name: ");
//					String productName;
//					do {
//						productName = sc.nextLine();
//					}while(productName.isEmpty());
//					
//					if(service.searchInventoryByProductName(productName) == null) {
//						System.out.println("There is no product on this name: "+productName);
//					}
//					else {
//						System.out.printf("%-25s%-50s%-15s%-15s","Inventory ID", "Product information", "quntityInStock", "lastStockUpdate");
//						System.out.println();
//						System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
//						service.searchInventoryByProductName(productName).forEach(e->{
//							System.out.println(e);
//						});	
//					}
//					System.out.println();
//					break;
				case 3:
					System.out.println("Welcome Back Main Section......!!!");
					return ;
				default:
					System.out.println("Please select correct option....");
				}
			}while(search != 4);
			} catch(Exception e) {
				System.out.println();
				System.out.println("Something error. Please try again.....");
				System.out.println();
				sc.nextLine();
				searchInventory();
			} 
		
	}
	
	private void displayInventory() {
		
		try {
			System.out.println("--------------DISPLAY INVENTORY DETAILS--------------");
			System.out.printf("%-25s%-50s%-15s%-15s","Inventory ID", "Product information", "quntityInStock", "lastStockUpdate");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			service.viewInventory().forEach(e -> {
				System.out.println(e);
			});
			System.out.println();			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
		} 
		
	}
	
	private void updateInventory() {
		
		try {
			System.out.println("--------------UPDATE INVENTORY DETAILS--------------");
			System.out.print("Enter number of inventories: ");
			int noOfInventories= sc.nextInt();
			sc.nextLine();
			while(noOfInventories<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfInventories = sc.nextInt();
				sc.nextLine();
			}
			System.out.println("Enter the inventory details formate is : \n[INVENTORY_ID:PRODUCT_ID:QUNTITY_IN_STOCK(NUMBER)]");
			int success = 0;
			for(int i = 0; i < noOfInventories; i++) {
				String inventoryDetails;
				do {
					inventoryDetails = sc.nextLine();
				}while(inventoryDetails.isEmpty());
				if(service.updateInventory(inventoryDetails)) {
					success++;
				}
			}
			
			if(success==0) System.out.println("inventories/inventory not added....");
			else if(success==1) System.out.println("Successfully "+success+" inventory added.");
			else if(success>1) System.out.println("Successfully "+success+" inventories added.");			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			updateInventory();
		} 
		
	}
	
	private void deleteInventory() {
		
		try {
			System.out.println("--------------DELETE INVENTORY DETAILS--------------");
			System.out.print("Enter number of inventory: ");
			int noOfInventories = sc.nextInt();
			while(noOfInventories<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfInventories = sc.nextInt();
			}
			System.out.println("Enter the Inventory Id's :");
			int success = 0;
			for(int i = 0; i < noOfInventories; i++) {
				String inventoryId;
				do {
					inventoryId = sc.nextLine();
				}while(inventoryId.isEmpty());
				if(service.deleteInventory(inventoryId)) {
					success++;
				}
				else {
					System.out.println("inventory Id is not exists.");
				}
			}
			
			
			if(success == 0) System.out.println("inventories/inventory not deleted....");
			else if(success == 1) System.out.println("Successfully "+success+" inventory deleted.");
			else if(success > 1) System.out.println("Successfully "+success+" inventories deleted.");			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			deleteInventory();
		} 
		
	}
	
	private void addInventory() {
		try {
			System.out.println("--------------ADDING INVENTORY DETAILS--------------");
			System.out.print("Enter number of inventory: ");
			int noOfInventories= sc.nextInt();
			sc.nextLine();
			while(noOfInventories<=0) {
				System.out.println("Your enter number is wrong... ");
				noOfInventories = sc.nextInt();
				sc.nextLine();
			}
			System.out.println("Enter the inventory details formate is : \n[PRODUCT_ID:QUNTITY_IN_STOCK(NUMBER)]");
			int success = 0;
			for(int i = 0; i < noOfInventories; i++) {
				String inventoryDetails;
				do {
					inventoryDetails = sc.nextLine();
				}while(inventoryDetails.isEmpty());
				String inventoryId = service.addInventory(inventoryDetails);
				if( inventoryId != null) {
					System.out.println(inventoryId);
					success++;
				}
			}
			
			if(success==0) System.out.println("inventories/inventory not added....");
			else if(success==1) System.out.println("Successfully "+success+" inventory added.");
			else if(success>1) System.out.println("Successfully "+success+" inventories added.");			
		} catch(Exception e) {
			System.out.println();
			System.out.println("Something error. Please try again.....");
			System.out.println();
			sc.nextLine();
			addInventory();
		} 
	}
	
	
	
	

}
