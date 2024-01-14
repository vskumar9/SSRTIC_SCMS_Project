package com.client;

import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {
    	// call userInterface method
    	System.out.println("----------WELCOME TO ADMIN DASHBOARD----------");
    	userInterface();
    }
    
    // Helps method to run application
    private static void userInterface() {
    	try (Scanner sc = new Scanner(System.in)) {
    		try {
    			int choice;
    			do {
    				System.out.print("1. Products\n2. Suppliers\n3. Inventory\n4. Warehouse\n5. Exit\nEnter your choice: ");
    				choice = sc.nextInt();
    				
    				switch(choice) {
    				case 1:
    					// Call the productUserInterface class method product
    					new ProductUserInterface().productSection();
    					break;
    				case 2:
    					// Call the supplierUserInterface class method supplier
    					new SupplierUerInterface().supplierSection();
    					break;	
    				case 3:
    					// Call the inventoryUserInterface class method inventory
    					new InventoryUserInterface().inventorySection();
    					break;
    				case 4:
    					// Call the warehouseUserInterface class method warehouse
    					new WarehouseUserInterface().warehouseSection();
    					break;
    				case 5:
    					System.out.println("Closing Application.......\nThank you.");
    					return;
    				default:
    					System.out.println("Your choice is wrong. Please select correct option.");
    					break;
    				}
    			}while(choice!=5);    			
    		} catch(Exception e) {
    			System.out.println();
    			System.out.println("Something error. Please try again.....");
    			System.out.println();
    			sc.nextLine();
    			userInterface();
    		} 		}
    }
}
