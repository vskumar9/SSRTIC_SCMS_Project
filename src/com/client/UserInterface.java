package com.client;

import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {
    	// call userInterface method
    	System.out.println("----------WELCOME TO MAIN DASHBOARD----------");
    	userInterface();
    }
    
    // Helps method to run application
    private static void userInterface() {
    	try (Scanner sc = new Scanner(System.in)) {
    		try {
    			int choice;
    			do {
    				System.out.print("1. Products\n2. Suppliers\nEnter your choice: ");
    				choice = sc.nextInt();
    				
    				switch(choice) {
    				case 1:
    					// Call the productUserInterface class method product
    					new ProductUserInterface().product();
    					break;
    				case 2:
    					// Call the supplierUserInterface class method supplier
    					new SupplierUerInterface().supplier();
    					break;	
    				}
    			}while(choice!=6);    			
    		} catch(Exception e) {
    			System.out.println();
    			System.out.println("Something error. Please try again.....");
    			System.out.println();
    		} finally {
    			userInterface();
    		}
		}
    }
}
