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
    				System.out.print("1. Products\n2. Suppliers\n3. Exit\nEnter your choice: ");
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
    					System.out.println("Closing Application.......\nThank you.");
    					return;
    					
    				}
    			}while(choice!=6);    			
    		} catch(Exception e) {
    			System.out.println();
    			System.out.println("Something error. Please try again.....");
    			System.out.println(e);
    			e.printStackTrace();
    			System.out.println();
    			userInterface();
    		} 		}
    }
}
